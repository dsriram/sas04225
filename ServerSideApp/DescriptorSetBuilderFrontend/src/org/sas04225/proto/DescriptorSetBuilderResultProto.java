// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: DescriptorSetBuilderResult.proto

package org.sas04225.proto;

public final class DescriptorSetBuilderResultProto {
  private DescriptorSetBuilderResultProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface DescriptorSetBuilderResultOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // required string group_name = 1;
    boolean hasGroupName();
    String getGroupName();
    
    // required uint32 descriptor_count = 2;
    boolean hasDescriptorCount();
    int getDescriptorCount();
    
    // required uint64 startIndex = 3;
    boolean hasStartIndex();
    long getStartIndex();
    
    // required uint64 endIndex = 4;
    boolean hasEndIndex();
    long getEndIndex();
  }
  public static final class DescriptorSetBuilderResult extends
      com.google.protobuf.GeneratedMessage
      implements DescriptorSetBuilderResultOrBuilder {
    // Use DescriptorSetBuilderResult.newBuilder() to construct.
    private DescriptorSetBuilderResult(Builder builder) {
      super(builder);
    }
    private DescriptorSetBuilderResult(boolean noInit) {}
    
    private static final DescriptorSetBuilderResult defaultInstance;
    public static DescriptorSetBuilderResult getDefaultInstance() {
      return defaultInstance;
    }
    
    public DescriptorSetBuilderResult getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.sas04225.proto.DescriptorSetBuilderResultProto.internal_static_org_sas04225_proto_DescriptorSetBuilderResult_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.sas04225.proto.DescriptorSetBuilderResultProto.internal_static_org_sas04225_proto_DescriptorSetBuilderResult_fieldAccessorTable;
    }
    
    private int bitField0_;
    // required string group_name = 1;
    public static final int GROUP_NAME_FIELD_NUMBER = 1;
    private java.lang.Object groupName_;
    public boolean hasGroupName() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public String getGroupName() {
      java.lang.Object ref = groupName_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          groupName_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getGroupNameBytes() {
      java.lang.Object ref = groupName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        groupName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // required uint32 descriptor_count = 2;
    public static final int DESCRIPTOR_COUNT_FIELD_NUMBER = 2;
    private int descriptorCount_;
    public boolean hasDescriptorCount() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public int getDescriptorCount() {
      return descriptorCount_;
    }
    
    // required uint64 startIndex = 3;
    public static final int STARTINDEX_FIELD_NUMBER = 3;
    private long startIndex_;
    public boolean hasStartIndex() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public long getStartIndex() {
      return startIndex_;
    }
    
    // required uint64 endIndex = 4;
    public static final int ENDINDEX_FIELD_NUMBER = 4;
    private long endIndex_;
    public boolean hasEndIndex() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    public long getEndIndex() {
      return endIndex_;
    }
    
    private void initFields() {
      groupName_ = "";
      descriptorCount_ = 0;
      startIndex_ = 0L;
      endIndex_ = 0L;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;
      
      if (!hasGroupName()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasDescriptorCount()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasStartIndex()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasEndIndex()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getGroupNameBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeUInt32(2, descriptorCount_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeUInt64(3, startIndex_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeUInt64(4, endIndex_);
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getGroupNameBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(2, descriptorCount_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt64Size(3, startIndex_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt64Size(4, endIndex_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }
    
    public static org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResultOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return org.sas04225.proto.DescriptorSetBuilderResultProto.internal_static_org_sas04225_proto_DescriptorSetBuilderResult_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return org.sas04225.proto.DescriptorSetBuilderResultProto.internal_static_org_sas04225_proto_DescriptorSetBuilderResult_fieldAccessorTable;
      }
      
      // Construct using org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }
      
      public Builder clear() {
        super.clear();
        groupName_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        descriptorCount_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        startIndex_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000004);
        endIndex_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult.getDescriptor();
      }
      
      public org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult getDefaultInstanceForType() {
        return org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult.getDefaultInstance();
      }
      
      public org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult build() {
        org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult buildPartial() {
        org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult result = new org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.groupName_ = groupName_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.descriptorCount_ = descriptorCount_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.startIndex_ = startIndex_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.endIndex_ = endIndex_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult) {
          return mergeFrom((org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult other) {
        if (other == org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult.getDefaultInstance()) return this;
        if (other.hasGroupName()) {
          setGroupName(other.getGroupName());
        }
        if (other.hasDescriptorCount()) {
          setDescriptorCount(other.getDescriptorCount());
        }
        if (other.hasStartIndex()) {
          setStartIndex(other.getStartIndex());
        }
        if (other.hasEndIndex()) {
          setEndIndex(other.getEndIndex());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasGroupName()) {
          
          return false;
        }
        if (!hasDescriptorCount()) {
          
          return false;
        }
        if (!hasStartIndex()) {
          
          return false;
        }
        if (!hasEndIndex()) {
          
          return false;
        }
        return true;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              onChanged();
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                onChanged();
                return this;
              }
              break;
            }
            case 10: {
              bitField0_ |= 0x00000001;
              groupName_ = input.readBytes();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              descriptorCount_ = input.readUInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              startIndex_ = input.readUInt64();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              endIndex_ = input.readUInt64();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // required string group_name = 1;
      private java.lang.Object groupName_ = "";
      public boolean hasGroupName() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      public String getGroupName() {
        java.lang.Object ref = groupName_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          groupName_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setGroupName(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        groupName_ = value;
        onChanged();
        return this;
      }
      public Builder clearGroupName() {
        bitField0_ = (bitField0_ & ~0x00000001);
        groupName_ = getDefaultInstance().getGroupName();
        onChanged();
        return this;
      }
      void setGroupName(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000001;
        groupName_ = value;
        onChanged();
      }
      
      // required uint32 descriptor_count = 2;
      private int descriptorCount_ ;
      public boolean hasDescriptorCount() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public int getDescriptorCount() {
        return descriptorCount_;
      }
      public Builder setDescriptorCount(int value) {
        bitField0_ |= 0x00000002;
        descriptorCount_ = value;
        onChanged();
        return this;
      }
      public Builder clearDescriptorCount() {
        bitField0_ = (bitField0_ & ~0x00000002);
        descriptorCount_ = 0;
        onChanged();
        return this;
      }
      
      // required uint64 startIndex = 3;
      private long startIndex_ ;
      public boolean hasStartIndex() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public long getStartIndex() {
        return startIndex_;
      }
      public Builder setStartIndex(long value) {
        bitField0_ |= 0x00000004;
        startIndex_ = value;
        onChanged();
        return this;
      }
      public Builder clearStartIndex() {
        bitField0_ = (bitField0_ & ~0x00000004);
        startIndex_ = 0L;
        onChanged();
        return this;
      }
      
      // required uint64 endIndex = 4;
      private long endIndex_ ;
      public boolean hasEndIndex() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      public long getEndIndex() {
        return endIndex_;
      }
      public Builder setEndIndex(long value) {
        bitField0_ |= 0x00000008;
        endIndex_ = value;
        onChanged();
        return this;
      }
      public Builder clearEndIndex() {
        bitField0_ = (bitField0_ & ~0x00000008);
        endIndex_ = 0L;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:org.sas04225.proto.DescriptorSetBuilderResult)
    }
    
    static {
      defaultInstance = new DescriptorSetBuilderResult(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:org.sas04225.proto.DescriptorSetBuilderResult)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_org_sas04225_proto_DescriptorSetBuilderResult_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_org_sas04225_proto_DescriptorSetBuilderResult_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n DescriptorSetBuilderResult.proto\022\022org." +
      "sas04225.proto\"p\n\032DescriptorSetBuilderRe" +
      "sult\022\022\n\ngroup_name\030\001 \002(\t\022\030\n\020descriptor_c" +
      "ount\030\002 \002(\r\022\022\n\nstartIndex\030\003 \002(\004\022\020\n\010endInd" +
      "ex\030\004 \002(\004B5\n\022org.sas04225.protoB\037Descript" +
      "orSetBuilderResultProto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_org_sas04225_proto_DescriptorSetBuilderResult_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_org_sas04225_proto_DescriptorSetBuilderResult_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_org_sas04225_proto_DescriptorSetBuilderResult_descriptor,
              new java.lang.String[] { "GroupName", "DescriptorCount", "StartIndex", "EndIndex", },
              org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult.class,
              org.sas04225.proto.DescriptorSetBuilderResultProto.DescriptorSetBuilderResult.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }
  
  // @@protoc_insertion_point(outer_class_scope)
}
