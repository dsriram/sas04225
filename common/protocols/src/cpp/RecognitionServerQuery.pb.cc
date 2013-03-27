// Generated by the protocol buffer compiler.  DO NOT EDIT!

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "RecognitionServerQuery.pb.h"

#include <algorithm>

#include <google/protobuf/stubs/once.h>
#include <google/protobuf/io/coded_stream.h>
#include <google/protobuf/wire_format_lite_inl.h>
#include <google/protobuf/descriptor.h>
#include <google/protobuf/reflection_ops.h>
#include <google/protobuf/wire_format.h>
// @@protoc_insertion_point(includes)

namespace org {
namespace sas04225 {
namespace proto {

namespace {

const ::google::protobuf::Descriptor* Query_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  Query_reflection_ = NULL;
const ::google::protobuf::EnumDescriptor* QueryType_descriptor_ = NULL;
const ::google::protobuf::EnumDescriptor* QueryDataType_descriptor_ = NULL;

}  // namespace


void protobuf_AssignDesc_RecognitionServerQuery_2eproto() {
  protobuf_AddDesc_RecognitionServerQuery_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "RecognitionServerQuery.proto");
  GOOGLE_CHECK(file != NULL);
  Query_descriptor_ = file->message_type(0);
  static const int Query_offsets_[5] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(Query, request_id_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(Query, querytype_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(Query, querydatatype_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(Query, location_id_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(Query, querydata_),
  };
  Query_reflection_ =
    new ::google::protobuf::internal::GeneratedMessageReflection(
      Query_descriptor_,
      Query::default_instance_,
      Query_offsets_,
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(Query, _has_bits_[0]),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(Query, _unknown_fields_),
      -1,
      ::google::protobuf::DescriptorPool::generated_pool(),
      ::google::protobuf::MessageFactory::generated_factory(),
      sizeof(Query));
  QueryType_descriptor_ = file->enum_type(0);
  QueryDataType_descriptor_ = file->enum_type(1);
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
inline void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_RecognitionServerQuery_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
    Query_descriptor_, &Query::default_instance());
}

}  // namespace

void protobuf_ShutdownFile_RecognitionServerQuery_2eproto() {
  delete Query::default_instance_;
  delete Query_reflection_;
}

void protobuf_AddDesc_RecognitionServerQuery_2eproto() {
  static bool already_here = false;
  if (already_here) return;
  already_here = true;
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n\034RecognitionServerQuery.proto\022\022org.sas0"
    "4225.proto\"\257\001\n\005Query\022\022\n\nrequest_id\030\001 \002(\004"
    "\0220\n\tqueryType\030\002 \002(\0162\035.org.sas04225.proto"
    ".QueryType\0228\n\rqueryDataType\030\003 \002(\0162!.org."
    "sas04225.proto.QueryDataType\022\023\n\013location"
    "_id\030\004 \002(\r\022\021\n\tqueryData\030\005 \002(\014*8\n\tQueryTyp"
    "e\022\030\n\024LOCATION_BASED_QUERY\020\000\022\021\n\rGENERAL_Q"
    "UERY\020\001*5\n\rQueryDataType\022\016\n\nJPEG_IMAGE\020\000\022"
    "\024\n\020FREAK_DESCRIPTOR\020\001B1\n\022org.sas04225.pr"
    "otoB\033RecognitionServerQueryProto", 392);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "RecognitionServerQuery.proto", &protobuf_RegisterTypes);
  Query::default_instance_ = new Query();
  Query::default_instance_->InitAsDefaultInstance();
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_RecognitionServerQuery_2eproto);
}

// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_RecognitionServerQuery_2eproto {
  StaticDescriptorInitializer_RecognitionServerQuery_2eproto() {
    protobuf_AddDesc_RecognitionServerQuery_2eproto();
  }
} static_descriptor_initializer_RecognitionServerQuery_2eproto_;

const ::google::protobuf::EnumDescriptor* QueryType_descriptor() {
  protobuf_AssignDescriptorsOnce();
  return QueryType_descriptor_;
}
bool QueryType_IsValid(int value) {
  switch(value) {
    case 0:
    case 1:
      return true;
    default:
      return false;
  }
}

const ::google::protobuf::EnumDescriptor* QueryDataType_descriptor() {
  protobuf_AssignDescriptorsOnce();
  return QueryDataType_descriptor_;
}
bool QueryDataType_IsValid(int value) {
  switch(value) {
    case 0:
    case 1:
      return true;
    default:
      return false;
  }
}


// ===================================================================

#ifndef _MSC_VER
const int Query::kRequestIdFieldNumber;
const int Query::kQueryTypeFieldNumber;
const int Query::kQueryDataTypeFieldNumber;
const int Query::kLocationIdFieldNumber;
const int Query::kQueryDataFieldNumber;
#endif  // !_MSC_VER

Query::Query()
  : ::google::protobuf::Message() {
  SharedCtor();
}

void Query::InitAsDefaultInstance() {
}

Query::Query(const Query& from)
  : ::google::protobuf::Message() {
  SharedCtor();
  MergeFrom(from);
}

void Query::SharedCtor() {
  _cached_size_ = 0;
  request_id_ = GOOGLE_ULONGLONG(0);
  querytype_ = 0;
  querydatatype_ = 0;
  location_id_ = 0u;
  querydata_ = const_cast< ::std::string*>(&::google::protobuf::internal::kEmptyString);
  ::memset(_has_bits_, 0, sizeof(_has_bits_));
}

Query::~Query() {
  SharedDtor();
}

void Query::SharedDtor() {
  if (querydata_ != &::google::protobuf::internal::kEmptyString) {
    delete querydata_;
  }
  if (this != default_instance_) {
  }
}

void Query::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* Query::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return Query_descriptor_;
}

const Query& Query::default_instance() {
  if (default_instance_ == NULL) protobuf_AddDesc_RecognitionServerQuery_2eproto();  return *default_instance_;
}

Query* Query::default_instance_ = NULL;

Query* Query::New() const {
  return new Query;
}

void Query::Clear() {
  if (_has_bits_[0 / 32] & (0xffu << (0 % 32))) {
    request_id_ = GOOGLE_ULONGLONG(0);
    querytype_ = 0;
    querydatatype_ = 0;
    location_id_ = 0u;
    if (has_querydata()) {
      if (querydata_ != &::google::protobuf::internal::kEmptyString) {
        querydata_->clear();
      }
    }
  }
  ::memset(_has_bits_, 0, sizeof(_has_bits_));
  mutable_unknown_fields()->Clear();
}

bool Query::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!(EXPRESSION)) return false
  ::google::protobuf::uint32 tag;
  while ((tag = input->ReadTag()) != 0) {
    switch (::google::protobuf::internal::WireFormatLite::GetTagFieldNumber(tag)) {
      // required uint64 request_id = 1;
      case 1: {
        if (::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
            ::google::protobuf::internal::WireFormatLite::WIRETYPE_VARINT) {
          DO_((::google::protobuf::internal::WireFormatLite::ReadPrimitive<
                   ::google::protobuf::uint64, ::google::protobuf::internal::WireFormatLite::TYPE_UINT64>(
                 input, &request_id_)));
          set_has_request_id();
        } else {
          goto handle_uninterpreted;
        }
        if (input->ExpectTag(16)) goto parse_queryType;
        break;
      }
      
      // required .org.sas04225.proto.QueryType queryType = 2;
      case 2: {
        if (::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
            ::google::protobuf::internal::WireFormatLite::WIRETYPE_VARINT) {
         parse_queryType:
          int value;
          DO_((::google::protobuf::internal::WireFormatLite::ReadPrimitive<
                   int, ::google::protobuf::internal::WireFormatLite::TYPE_ENUM>(
                 input, &value)));
          if (org::sas04225::proto::QueryType_IsValid(value)) {
            set_querytype(static_cast< org::sas04225::proto::QueryType >(value));
          } else {
            mutable_unknown_fields()->AddVarint(2, value);
          }
        } else {
          goto handle_uninterpreted;
        }
        if (input->ExpectTag(24)) goto parse_queryDataType;
        break;
      }
      
      // required .org.sas04225.proto.QueryDataType queryDataType = 3;
      case 3: {
        if (::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
            ::google::protobuf::internal::WireFormatLite::WIRETYPE_VARINT) {
         parse_queryDataType:
          int value;
          DO_((::google::protobuf::internal::WireFormatLite::ReadPrimitive<
                   int, ::google::protobuf::internal::WireFormatLite::TYPE_ENUM>(
                 input, &value)));
          if (org::sas04225::proto::QueryDataType_IsValid(value)) {
            set_querydatatype(static_cast< org::sas04225::proto::QueryDataType >(value));
          } else {
            mutable_unknown_fields()->AddVarint(3, value);
          }
        } else {
          goto handle_uninterpreted;
        }
        if (input->ExpectTag(32)) goto parse_location_id;
        break;
      }
      
      // required uint32 location_id = 4;
      case 4: {
        if (::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
            ::google::protobuf::internal::WireFormatLite::WIRETYPE_VARINT) {
         parse_location_id:
          DO_((::google::protobuf::internal::WireFormatLite::ReadPrimitive<
                   ::google::protobuf::uint32, ::google::protobuf::internal::WireFormatLite::TYPE_UINT32>(
                 input, &location_id_)));
          set_has_location_id();
        } else {
          goto handle_uninterpreted;
        }
        if (input->ExpectTag(42)) goto parse_queryData;
        break;
      }
      
      // required bytes queryData = 5;
      case 5: {
        if (::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
            ::google::protobuf::internal::WireFormatLite::WIRETYPE_LENGTH_DELIMITED) {
         parse_queryData:
          DO_(::google::protobuf::internal::WireFormatLite::ReadBytes(
                input, this->mutable_querydata()));
        } else {
          goto handle_uninterpreted;
        }
        if (input->ExpectAtEnd()) return true;
        break;
      }
      
      default: {
      handle_uninterpreted:
        if (::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
            ::google::protobuf::internal::WireFormatLite::WIRETYPE_END_GROUP) {
          return true;
        }
        DO_(::google::protobuf::internal::WireFormat::SkipField(
              input, tag, mutable_unknown_fields()));
        break;
      }
    }
  }
  return true;
#undef DO_
}

void Query::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // required uint64 request_id = 1;
  if (has_request_id()) {
    ::google::protobuf::internal::WireFormatLite::WriteUInt64(1, this->request_id(), output);
  }
  
  // required .org.sas04225.proto.QueryType queryType = 2;
  if (has_querytype()) {
    ::google::protobuf::internal::WireFormatLite::WriteEnum(
      2, this->querytype(), output);
  }
  
  // required .org.sas04225.proto.QueryDataType queryDataType = 3;
  if (has_querydatatype()) {
    ::google::protobuf::internal::WireFormatLite::WriteEnum(
      3, this->querydatatype(), output);
  }
  
  // required uint32 location_id = 4;
  if (has_location_id()) {
    ::google::protobuf::internal::WireFormatLite::WriteUInt32(4, this->location_id(), output);
  }
  
  // required bytes queryData = 5;
  if (has_querydata()) {
    ::google::protobuf::internal::WireFormatLite::WriteBytes(
      5, this->querydata(), output);
  }
  
  if (!unknown_fields().empty()) {
    ::google::protobuf::internal::WireFormat::SerializeUnknownFields(
        unknown_fields(), output);
  }
}

::google::protobuf::uint8* Query::SerializeWithCachedSizesToArray(
    ::google::protobuf::uint8* target) const {
  // required uint64 request_id = 1;
  if (has_request_id()) {
    target = ::google::protobuf::internal::WireFormatLite::WriteUInt64ToArray(1, this->request_id(), target);
  }
  
  // required .org.sas04225.proto.QueryType queryType = 2;
  if (has_querytype()) {
    target = ::google::protobuf::internal::WireFormatLite::WriteEnumToArray(
      2, this->querytype(), target);
  }
  
  // required .org.sas04225.proto.QueryDataType queryDataType = 3;
  if (has_querydatatype()) {
    target = ::google::protobuf::internal::WireFormatLite::WriteEnumToArray(
      3, this->querydatatype(), target);
  }
  
  // required uint32 location_id = 4;
  if (has_location_id()) {
    target = ::google::protobuf::internal::WireFormatLite::WriteUInt32ToArray(4, this->location_id(), target);
  }
  
  // required bytes queryData = 5;
  if (has_querydata()) {
    target =
      ::google::protobuf::internal::WireFormatLite::WriteBytesToArray(
        5, this->querydata(), target);
  }
  
  if (!unknown_fields().empty()) {
    target = ::google::protobuf::internal::WireFormat::SerializeUnknownFieldsToArray(
        unknown_fields(), target);
  }
  return target;
}

int Query::ByteSize() const {
  int total_size = 0;
  
  if (_has_bits_[0 / 32] & (0xffu << (0 % 32))) {
    // required uint64 request_id = 1;
    if (has_request_id()) {
      total_size += 1 +
        ::google::protobuf::internal::WireFormatLite::UInt64Size(
          this->request_id());
    }
    
    // required .org.sas04225.proto.QueryType queryType = 2;
    if (has_querytype()) {
      total_size += 1 +
        ::google::protobuf::internal::WireFormatLite::EnumSize(this->querytype());
    }
    
    // required .org.sas04225.proto.QueryDataType queryDataType = 3;
    if (has_querydatatype()) {
      total_size += 1 +
        ::google::protobuf::internal::WireFormatLite::EnumSize(this->querydatatype());
    }
    
    // required uint32 location_id = 4;
    if (has_location_id()) {
      total_size += 1 +
        ::google::protobuf::internal::WireFormatLite::UInt32Size(
          this->location_id());
    }
    
    // required bytes queryData = 5;
    if (has_querydata()) {
      total_size += 1 +
        ::google::protobuf::internal::WireFormatLite::BytesSize(
          this->querydata());
    }
    
  }
  if (!unknown_fields().empty()) {
    total_size +=
      ::google::protobuf::internal::WireFormat::ComputeUnknownFieldsSize(
        unknown_fields());
  }
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = total_size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
  return total_size;
}

void Query::MergeFrom(const ::google::protobuf::Message& from) {
  GOOGLE_CHECK_NE(&from, this);
  const Query* source =
    ::google::protobuf::internal::dynamic_cast_if_available<const Query*>(
      &from);
  if (source == NULL) {
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
    MergeFrom(*source);
  }
}

void Query::MergeFrom(const Query& from) {
  GOOGLE_CHECK_NE(&from, this);
  if (from._has_bits_[0 / 32] & (0xffu << (0 % 32))) {
    if (from.has_request_id()) {
      set_request_id(from.request_id());
    }
    if (from.has_querytype()) {
      set_querytype(from.querytype());
    }
    if (from.has_querydatatype()) {
      set_querydatatype(from.querydatatype());
    }
    if (from.has_location_id()) {
      set_location_id(from.location_id());
    }
    if (from.has_querydata()) {
      set_querydata(from.querydata());
    }
  }
  mutable_unknown_fields()->MergeFrom(from.unknown_fields());
}

void Query::CopyFrom(const ::google::protobuf::Message& from) {
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void Query::CopyFrom(const Query& from) {
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

bool Query::IsInitialized() const {
  if ((_has_bits_[0] & 0x0000001f) != 0x0000001f) return false;
  
  return true;
}

void Query::Swap(Query* other) {
  if (other != this) {
    std::swap(request_id_, other->request_id_);
    std::swap(querytype_, other->querytype_);
    std::swap(querydatatype_, other->querydatatype_);
    std::swap(location_id_, other->location_id_);
    std::swap(querydata_, other->querydata_);
    std::swap(_has_bits_[0], other->_has_bits_[0]);
    _unknown_fields_.Swap(&other->_unknown_fields_);
    std::swap(_cached_size_, other->_cached_size_);
  }
}

::google::protobuf::Metadata Query::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = Query_descriptor_;
  metadata.reflection = Query_reflection_;
  return metadata;
}


// @@protoc_insertion_point(namespace_scope)

}  // namespace proto
}  // namespace sas04225
}  // namespace org

// @@protoc_insertion_point(global_scope)