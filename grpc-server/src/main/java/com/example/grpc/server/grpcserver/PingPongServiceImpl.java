package com.example.grpc.server.grpcserver;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class PingPongServiceImpl extends PingPongServiceGrpc.PingPongServiceImplBase {
    @Override
    public void ping(PingRequest request, StreamObserver<PongResponse> responseObserver) {        
		String ping = new StringBuilder().append("pong").toString();     

		PongResponse response = PongResponse.newBuilder().setPong(ping).build();      

		responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
	
}

@GrpcService
class MatrixServiceImpl extends MatrixServiceGrpc.MatrixServiceImplBase {
    @Override
    public void addBlock(MatrixRequest request, StreamObserver<MatrixReply> reply)
	{
		System.out.println("Request received from client:\n" + request);
		int C=request.getA()+request.getB();

		MatrixReply response = MatrixReply.newBuilder().setC(C).build();
		reply.onNext(response);
		reply.onCompleted();
	}
    
	@Override
    public void multiplyBlock(MatrixRequest request, StreamObserver<MatrixReply> reply)
    {
		System.out.println("Request received from client:\n" + request);
		int C=request.getA()*request.getB();

		MatrixReply response = MatrixReply.newBuilder().setC(C).build();
		reply.onNext(response);
		reply.onCompleted();
    }
}