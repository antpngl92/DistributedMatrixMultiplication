package com.example.grpc.server.grpcserver;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 * <pre>
 * The matrix service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: MatrixService.proto")
public final class MatrixServiceGrpc {

  private MatrixServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.grpc.server.grpcserver.MatrixService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.example.grpc.server.grpcserver.MatrixRequest,
      com.example.grpc.server.grpcserver.MatrixReply> METHOD_MULTIPLY_BLOCK =
      io.grpc.MethodDescriptor.<com.example.grpc.server.grpcserver.MatrixRequest, com.example.grpc.server.grpcserver.MatrixReply>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.example.grpc.server.grpcserver.MatrixService", "MultiplyBlock"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.grpc.server.grpcserver.MatrixRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.grpc.server.grpcserver.MatrixReply.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.example.grpc.server.grpcserver.MatrixRequest,
      com.example.grpc.server.grpcserver.MatrixReply> METHOD_ADD_BLOCK =
      io.grpc.MethodDescriptor.<com.example.grpc.server.grpcserver.MatrixRequest, com.example.grpc.server.grpcserver.MatrixReply>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.example.grpc.server.grpcserver.MatrixService", "AddBlock"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.grpc.server.grpcserver.MatrixRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.example.grpc.server.grpcserver.MatrixReply.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MatrixServiceStub newStub(io.grpc.Channel channel) {
    return new MatrixServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MatrixServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MatrixServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MatrixServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MatrixServiceFutureStub(channel);
  }

  /**
   * <pre>
   * The matrix service definition.
   * </pre>
   */
  public static abstract class MatrixServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void multiplyBlock(com.example.grpc.server.grpcserver.MatrixRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.server.grpcserver.MatrixReply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_MULTIPLY_BLOCK, responseObserver);
    }

    /**
     */
    public void addBlock(com.example.grpc.server.grpcserver.MatrixRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.server.grpcserver.MatrixReply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ADD_BLOCK, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_MULTIPLY_BLOCK,
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.server.grpcserver.MatrixRequest,
                com.example.grpc.server.grpcserver.MatrixReply>(
                  this, METHODID_MULTIPLY_BLOCK)))
          .addMethod(
            METHOD_ADD_BLOCK,
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.server.grpcserver.MatrixRequest,
                com.example.grpc.server.grpcserver.MatrixReply>(
                  this, METHODID_ADD_BLOCK)))
          .build();
    }
  }

  /**
   * <pre>
   * The matrix service definition.
   * </pre>
   */
  public static final class MatrixServiceStub extends io.grpc.stub.AbstractStub<MatrixServiceStub> {
    private MatrixServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MatrixServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MatrixServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MatrixServiceStub(channel, callOptions);
    }

    /**
     */
    public void multiplyBlock(com.example.grpc.server.grpcserver.MatrixRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.server.grpcserver.MatrixReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_MULTIPLY_BLOCK, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addBlock(com.example.grpc.server.grpcserver.MatrixRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.server.grpcserver.MatrixReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_BLOCK, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The matrix service definition.
   * </pre>
   */
  public static final class MatrixServiceBlockingStub extends io.grpc.stub.AbstractStub<MatrixServiceBlockingStub> {
    private MatrixServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MatrixServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MatrixServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MatrixServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpc.server.grpcserver.MatrixReply multiplyBlock(com.example.grpc.server.grpcserver.MatrixRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_MULTIPLY_BLOCK, getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.server.grpcserver.MatrixReply addBlock(com.example.grpc.server.grpcserver.MatrixRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ADD_BLOCK, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The matrix service definition.
   * </pre>
   */
  public static final class MatrixServiceFutureStub extends io.grpc.stub.AbstractStub<MatrixServiceFutureStub> {
    private MatrixServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MatrixServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MatrixServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MatrixServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.server.grpcserver.MatrixReply> multiplyBlock(
        com.example.grpc.server.grpcserver.MatrixRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_MULTIPLY_BLOCK, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.server.grpcserver.MatrixReply> addBlock(
        com.example.grpc.server.grpcserver.MatrixRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_BLOCK, getCallOptions()), request);
    }
  }

  private static final int METHODID_MULTIPLY_BLOCK = 0;
  private static final int METHODID_ADD_BLOCK = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MatrixServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MatrixServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MULTIPLY_BLOCK:
          serviceImpl.multiplyBlock((com.example.grpc.server.grpcserver.MatrixRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.server.grpcserver.MatrixReply>) responseObserver);
          break;
        case METHODID_ADD_BLOCK:
          serviceImpl.addBlock((com.example.grpc.server.grpcserver.MatrixRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.server.grpcserver.MatrixReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class MatrixServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpc.server.grpcserver.MatrixServiceOuterClass.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MatrixServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MatrixServiceDescriptorSupplier())
              .addMethod(METHOD_MULTIPLY_BLOCK)
              .addMethod(METHOD_ADD_BLOCK)
              .build();
        }
      }
    }
    return result;
  }
}
