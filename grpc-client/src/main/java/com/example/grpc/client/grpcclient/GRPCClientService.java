package com.example.grpc.client.grpcclient;

import com.example.grpc.server.grpcserver.MatrixRequest;
import com.example.grpc.server.grpcserver.MatrixReply;
import com.example.grpc.server.grpcserver.MatrixServiceGrpc;

import com.example.grpc.server.grpcserver.PingRequest;
import com.example.grpc.server.grpcserver.PongResponse;
import com.example.grpc.server.grpcserver.PingPongServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File; 
import java.io.IOException;
import java.util.*;


import com.example.grpc.client.model.FileUploadResponse;

@Service
public class GRPCClientService {

        private String fileName;
        private String uploadFilePath;
        private String contentType;
        private File dest;

        @Value("${matrix.symbols}")
        private String matrixSymbols;

        public String ping() {
                ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();        
                PingPongServiceGrpc.PingPongServiceBlockingStub stub = PingPongServiceGrpc.newBlockingStub(channel);        
                PongResponse helloResponse = stub.ping(PingRequest.newBuilder()
                .setPing("")
                .build());   

                channel.shutdown();        
                return helloResponse.getPong();
        }

        public FileUploadResponse fileUpload(@RequestParam("file") MultipartFile file){

                fileName = file.getOriginalFilename();
                uploadFilePath = "/home/anton/Desktop/DS_CW/grpcNew/Files";
                contentType = file.getContentType();
                dest = new File(uploadFilePath + '/' + fileName);

                if (!dest.getParentFile().exists())  dest.getParentFile().mkdirs();
                    

                try { file.transferTo(dest); }
                catch (Exception e) { return new FileUploadResponse(fileName, contentType, "File is not provided, please add a file!!! " + e.getMessage()); }

                // Get each matrix from file 
                String matrixA_temp = txt2String(dest).split(matrixSymbols)[0];
                String matrixB_temp = txt2String(dest).split(matrixSymbols)[1];

                // For each matrix split rows and col from data 
                String[] rowCol_dataA = matrixA_temp.split(";");
                String[] rowCol_dataB = matrixB_temp.split(";");

                // Convert each string matrix to int 2D matrix
                int[][] matrixA = convertToMatrix(rowCol_dataA);
                int[][] matrixB = convertToMatrix(rowCol_dataB);

                // If not square matrix
                if(matrixA.length != matrixA[0].length || matrixB.length != matrixB[0].length){
                        String data  = "Matrix A: " + matrixA.length  + "x" + matrixA[0].length;
                               data += "  Matrix B: " + matrixB.length  + "x" + matrixB[0].length;
                        return new FileUploadResponse(fileName, contentType, "Rows and Columns of the Matrices should be equal size!!! " + data);
                }
                grpc(matrixA, matrixB);
                return new FileUploadResponse(fileName, contentType, "File Successfully Uploaded");
        }

        public void grpc(int[][]a, int[][]b){
                // (col-or-row num / 2)^2 = number of 2x2 matrices 

                ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();        
                MatrixServiceGrpc.MatrixServiceBlockingStub stub = MatrixServiceGrpc.newBlockingStub(channel);       
                int A[][] = a;
                int B[][] = b;

                MatrixReply A3M1=stub.multiplyBlock(MatrixRequest.newBuilder()//First Result Block Calculation
                .setA00(A[0][0])
                .setA01(A[0][1])
                .setA10(A[1][0])
                .setA11(A[1][1])
                .setB00(B[0][0])
                .setB01(B[0][1])
                .setB10(B[1][0])
                .setB11(B[1][1])
                .build());
                MatrixReply A3M2=stub.multiplyBlock(MatrixRequest.newBuilder()
                .setA00(A[0][2])
                .setA01(A[0][3])
                .setA10(A[1][2])
                .setA11(A[1][3])
                .setB00(B[2][0])
                .setB01(B[2][1])
                .setB10(B[3][0])
                .setB11(B[3][1])
                .build());
                MatrixReply A3=stub.addBlock(MatrixRequest.newBuilder()
                .setA00(A3M1.getC00())
                .setA01(A3M1.getC01())
                .setA10(A3M1.getC10())
                .setA11(A3M1.getC11())
                .setB00(A3M2.getC00())
                .setB01(A3M2.getC01())
                .setB10(A3M2.getC10())
                .setB11(A3M2.getC11())
                .build());
                MatrixReply B3M1=stub.multiplyBlock(MatrixRequest.newBuilder()//Second Result Block Calculation
                .setA00(A[0][0])
                .setA01(A[0][1])
                .setA10(A[1][0])
                .setA11(A[1][1])
                .setB00(B[0][2])
                .setB01(B[0][3])
                .setB10(B[1][2])
                .setB11(B[1][3])
                .build());
                MatrixReply B3M2=stub.multiplyBlock(MatrixRequest.newBuilder()
                .setA00(A[0][2])
                .setA01(A[0][3])
                .setA10(A[1][2])
                .setA11(A[1][3])
                .setB00(B[2][2])
                .setB01(B[2][3])
                .setB10(B[3][2])
                .setB11(B[3][3])
                .build());
                MatrixReply B3=stub.addBlock(MatrixRequest.newBuilder()
                .setA00(B3M1.getC00())
                .setA01(B3M1.getC01())
                .setA10(B3M1.getC10())
                .setA11(B3M1.getC11())
                .setB00(B3M2.getC00())
                .setB01(B3M2.getC01())
                .setB10(B3M2.getC10())
                .setB11(B3M2.getC11())
                .build());
                MatrixReply C3M1=stub.multiplyBlock(MatrixRequest.newBuilder()//Third Result Block Calculation
                .setA00(A[2][0])
                .setA01(A[2][1])
                .setA10(A[3][0])
                .setA11(A[3][1])
                .setB00(B[0][0])
                .setB01(B[0][1])
                .setB10(B[1][0])
                .setB11(B[1][1])
                .build());
                MatrixReply C3M2=stub.multiplyBlock(MatrixRequest.newBuilder()
                .setA00(A[2][2])
                .setA01(A[2][3])
                .setA10(A[3][2])
                .setA11(A[3][3])
                .setB00(B[2][0])
                .setB01(B[2][1])
                .setB10(B[3][0])
                .setB11(B[3][1])
                .build());
                MatrixReply C3=stub.addBlock(MatrixRequest.newBuilder()
                .setA00(C3M1.getC00())
                .setA01(C3M1.getC01())
                .setA10(C3M1.getC10())
                .setA11(C3M1.getC11())
                .setB00(C3M2.getC00())
                .setB01(C3M2.getC01())
                .setB10(C3M2.getC10())
                .setB11(C3M2.getC11())
                .build());
                MatrixReply D3M1=stub.multiplyBlock(MatrixRequest.newBuilder()//Fourth Result Block Calculation
                .setA00(A[2][0])
                .setA01(A[2][1])
                .setA10(A[3][0])
                .setA11(A[3][1])
                .setB00(B[0][2])
                .setB01(B[0][3])
                .setB10(B[1][2])
                .setB11(B[1][3])
                .build());
                MatrixReply D3M2=stub.multiplyBlock(MatrixRequest.newBuilder()
                .setA00(A[2][2])
                .setA01(A[2][3])
                .setA10(A[3][2])
                .setA11(A[3][3])
                .setB00(B[2][2])
                .setB01(B[2][3])
                .setB10(B[3][2])
                .setB11(B[3][3])
                .build());
                MatrixReply D3=stub.addBlock(MatrixRequest.newBuilder()
                .setA00(D3M1.getC00())
                .setA01(D3M1.getC01())
                .setA10(D3M1.getC10())
                .setA11(D3M1.getC11())
                .setB00(D3M2.getC00())
                .setB01(D3M2.getC01())
                .setB10(D3M2.getC10())
                .setB11(D3M2.getC11())
                .build());
                System.out.println("Final Answer");
                System.out.println(A3.getC00()+" "+A3.getC01()+" "+B3.getC00()+" "+B3.getC01());
                System.out.println(A3.getC10()+" "+A3.getC11()+" "+B3.getC10()+" "+B3.getC11());
                System.out.println(C3.getC00()+" "+A3.getC01()+" "+D3.getC00()+" "+B3.getC01());
                System.out.println(C3.getC10()+" "+C3.getC11()+" "+D3.getC10()+" "+D3.getC11());
                channel.shutdown();
        }

        public static int[][] convertToMatrix(String[] data){
                // Get rows and data into int var. 
                int row = Integer.parseInt(data[0].substring(0, 2).replaceAll("[\\n\\t ]", ""));
                int col = Integer.parseInt(data[0].substring(3, 4).replaceAll("[\\n\\t ]", ""));
 
                String[] matrixData_temp = data[1].split(" ");
                int[][] matrix = new int[row][col];
                int temp_matrix_index = 0; 
                 
                for(int i = 0; i < row; i++){
                        for(int j = 0; j < col; j++){
                                matrix[i][j] = Integer.parseInt(matrixData_temp[temp_matrix_index].replaceAll("[\\n\\t ]", ""));
                                temp_matrix_index++;
                        }
                }
                return matrix;
        }


        public static String txt2String(File file) {
                StringBuilder result = new StringBuilder();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String s = null;
                    while ((s = br.readLine()) != null) {
                        result.append(System.lineSeparator() + s);
                    }
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result.toString();
        }



}
