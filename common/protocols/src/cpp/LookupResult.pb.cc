// Generated by the protocol buffer compiler.  DO NOT EDIT!

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "LookupResult.pb.h"

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

const ::google::protobuf::Descriptor* LookupResult_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  LookupResult_reflection_ = NULL;
const ::google::protobuf::Descriptor* DMatch_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  DMatch_reflection_ = NULL;

}  // namespace


void protobuf_AssignDesc_LookupResult_2eproto() {
  protobuf_AddDesc_LookupResult_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "LookupResult.proto");
  GOOGLE_CHECK(file != NULL);
  LookupResult_descriptor_ = file->message_type(0);
  static const int LookupResult_offsets_[2] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(LookupResult, request_id_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(LookupResult, matches_),
  };
  LookupResult_reflection_ =
    new ::google::protobuf::internal::GeneratedMessageReflection(
      LookupResult_descriptor_,
      LookupResult::default_instance_,
      LookupResult_offsets_,
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(LookupResult, _has_bits_[0]),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(LookupResult, _unknown_fields_),
      -1,
      ::google::protobuf::DescriptorPool::generated_pool(),
      ::google::protobuf::MessageFactory::generated_factory(),
      sizeof(LookupResult));
  DMatch_descriptor_ = file->message_type(1);
  static const int DMatch_offsets_[2] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(DMatch, distance_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(DMatch, trainidx_),
  };
  DMatch_reflection_ =
    new ::google::protobuf::internal::GeneratedMessageReflection(
      DMatch_descriptor_,
      DMatch::default_instance_,
      DMatch_offsets_,
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(DMatch, _has_bits_[0]),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(DMatch, _unknown_fields_),
      -1,
      ::google::protobuf::DescriptorPool::generated_pool(),
      ::google::protobuf::MessageFactory::generated_factory(),
      sizeof(DMatch));
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
inline void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_LookupResult_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
    LookupResult_descriptor_, &LookupResult::default_instance());
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
    DMatch_descriptor_, &DMatch::default_instance());
}

}  // namespace

void protobuf_ShutdownFile_LookupResult_2eproto() {
  delete LookupResult::default_instance_;
  delete LookupResult_reflection_;
  delete DMatch::default_instance_;
  delete DMatch_reflection_;
}

void protobuf_AddDesc_LookupResult_2eproto() {
  static bool already_here = false;
  if (already_here) return;
  already_here = true;
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n\022LookupResult.proto\022\022org.sas04225.proto"
    "\"O\n\014LookupResult\022\022\n\nrequest_id\030\001 \002(\004\022+\n\007"
    "matches\030\002 \003(\0132\032.org.sas04225.proto.DMatc"
    "h\",\n\006DMatch\022\020\n\010distance\030\001 \002(\r\022\020\n\010trainId"
    "x\030\002 \002(\004B\'\n\022org.sas04225.protoB\021LookupRes"
    "ultProto", 208);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "LookupResult.proto", &protobuf_RegisterTypes);
  LookupResult::default_instance_ = new LookupResult();
  DMatch::default_instance_ = new DMatch();
  LookupResult::default_instance_->InitAsDefaultInstance();
  DMatch::default_instance_->InitAsDefaultInstance();
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_LookupResult_2eproto);
}

// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_LookupResult_2eproto {
  StaticDescriptorInitializer_LookupResult_2eproto() {
    protobuf_AddDesc_LookupResult_2eproto();
  }
} static_descriptor_initializer_LookupResult_2eproto_;


// ===================================================================

#ifndef _MSC_VER
const int LookupResult::kRequestIdFieldNumber;
const int LookupResult::kMatchesFieldNumber;
#endif  // !_MSC_VER

LookupResult::LookupResult()
  : ::google::protobuf::Message() {
  SharedCtor();
}

void LookupResult::InitAsDefaultInstance() {
}

LookupResult::LookupResult(const LookupResult& from)
  : ::google::protobuf::Message() {
  SharedCtor();
  MergeFrom(from);
}

void LookupResult::SharedCtor() {
  _cached_size_ = 0;
  request_id_ = GOOGLE_ULONGLONG(0);
  ::memset(_has_bits_, 0, sizeof(_has_bits_));
}

LookupResult::~LookupResult() {
  SharedDtor();
}

void LookupResult::SharedDtor() {
  if (this != default_instance_) {
  }
}

void LookupResult::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* LookupResult::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return LookupResult_descriptor_;
}

const LookupResult& LookupResult::default_instance() {
  if (default_instance_ == NULL) protobuf_AddDesc_LookupResult_2eproto();  return *default_instance_;
}

LookupResult* LookupResult::default_instance_ = NULL;

LookupResult* LookupResult::New() const {
  return new LookupResult;
}

void LookupResult::Clear() {
  if (_has_bits_[0 / 32] & (0xffu << (0 % 32))) {
    request_id_ = GOOGLE_ULONGLONG(0);
  }
  matches_.Clear();
  ::memset(_has_bits_, 0, sizeof(_has_bits_));
  mutable_unknown_fields()->Clear();
}

bool LookupResult::MergePartialFromCodedStream(
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
        if (input->ExpectTag(18)) goto parse_matches;
        break;
      }
      
      // repeated .org.sas04225.proto.DMatch matches = 2;
      case 2: {
        if (::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
            ::google::protobuf::internal::WireFormatLite::WIRETYPE_LENGTH_DELIMITED) {
         parse_matches:
          DO_(::google::protobuf::internal::WireFormatLite::ReadMessageNoVirtual(
                input, add_matches()));
        } else {
          goto handle_uninterpreted;
        }
        if (input->ExpectTag(18)) goto parse_matches;
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

void LookupResult::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // required uint64 request_id = 1;
  if (has_request_id()) {
    ::google::protobuf::internal::WireFormatLite::WriteUInt64(1, this->request_id(), output);
  }
  
  // repeated .org.sas04225.proto.DMatch matches = 2;
  for (int i = 0; i < this->matches_size(); i++) {
    ::google::protobuf::internal::WireFormatLite::WriteMessageMaybeToArray(
      2, this->matches(i), output);
  }
  
  if (!unknown_fields().empty()) {
    ::google::protobuf::internal::WireFormat::SerializeUnknownFields(
        unknown_fields(), output);
  }
}

::google::protobuf::uint8* LookupResult::SerializeWithCachedSizesToArray(
    ::google::protobuf::uint8* target) const {
  // required uint64 request_id = 1;
  if (has_request_id()) {
    target = ::google::protobuf::internal::WireFormatLite::WriteUInt64ToArray(1, this->request_id(), target);
  }
  
  // repeated .org.sas04225.proto.DMatch matches = 2;
  for (int i = 0; i < this->matches_size(); i++) {
    target = ::google::protobuf::internal::WireFormatLite::
      WriteMessageNoVirtualToArray(
        2, this->matches(i), target);
  }
  
  if (!unknown_fields().empty()) {
    target = ::google::protobuf::internal::WireFormat::SerializeUnknownFieldsToArray(
        unknown_fields(), target);
  }
  return target;
}

int LookupResult::ByteSize() const {
  int total_size = 0;
  
  if (_has_bits_[0 / 32] & (0xffu << (0 % 32))) {
    // required uint64 request_id = 1;
    if (has_request_id()) {
      total_size += 1 +
        ::google::protobuf::internal::WireFormatLite::UInt64Size(
          this->request_id());
    }
    
  }
  // repeated .org.sas04225.proto.DMatch matches = 2;
  total_size += 1 * this->matches_size();
  for (int i = 0; i < this->matches_size(); i++) {
    total_size +=
      ::google::protobuf::internal::WireFormatLite::MessageSizeNoVirtual(
        this->matches(i));
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

void LookupResult::MergeFrom(const ::google::protobuf::Message& from) {
  GOOGLE_CHECK_NE(&from, this);
  const LookupResult* source =
    ::google::protobuf::internal::dynamic_cast_if_available<const LookupResult*>(
      &from);
  if (source == NULL) {
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
    MergeFrom(*source);
  }
}

void LookupResult::MergeFrom(const LookupResult& from) {
  GOOGLE_CHECK_NE(&from, this);
  matches_.MergeFrom(from.matches_);
  if (from._has_bits_[0 / 32] & (0xffu << (0 % 32))) {
    if (from.has_request_id()) {
      set_request_id(from.request_id());
    }
  }
  mutable_unknown_fields()->MergeFrom(from.unknown_fields());
}

void LookupResult::CopyFrom(const ::google::protobuf::Message& from) {
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void LookupResult::CopyFrom(const LookupResult& from) {
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

bool LookupResult::IsInitialized() const {
  if ((_has_bits_[0] & 0x00000001) != 0x00000001) return false;
  
  for (int i = 0; i < matches_size(); i++) {
    if (!this->matches(i).IsInitialized()) return false;
  }
  return true;
}

void LookupResult::Swap(LookupResult* other) {
  if (other != this) {
    std::swap(request_id_, other->request_id_);
    matches_.Swap(&other->matches_);
    std::swap(_has_bits_[0], other->_has_bits_[0]);
    _unknown_fields_.Swap(&other->_unknown_fields_);
    std::swap(_cached_size_, other->_cached_size_);
  }
}

::google::protobuf::Metadata LookupResult::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = LookupResult_descriptor_;
  metadata.reflection = LookupResult_reflection_;
  return metadata;
}


// ===================================================================

#ifndef _MSC_VER
const int DMatch::kDistanceFieldNumber;
const int DMatch::kTrainIdxFieldNumber;
#endif  // !_MSC_VER

DMatch::DMatch()
  : ::google::protobuf::Message() {
  SharedCtor();
}

void DMatch::InitAsDefaultInstance() {
}

DMatch::DMatch(const DMatch& from)
  : ::google::protobuf::Message() {
  SharedCtor();
  MergeFrom(from);
}

void DMatch::SharedCtor() {
  _cached_size_ = 0;
  distance_ = 0u;
  trainidx_ = GOOGLE_ULONGLONG(0);
  ::memset(_has_bits_, 0, sizeof(_has_bits_));
}

DMatch::~DMatch() {
  SharedDtor();
}

void DMatch::SharedDtor() {
  if (this != default_instance_) {
  }
}

void DMatch::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* DMatch::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return DMatch_descriptor_;
}

const DMatch& DMatch::default_instance() {
  if (default_instance_ == NULL) protobuf_AddDesc_LookupResult_2eproto();  return *default_instance_;
}

DMatch* DMatch::default_instance_ = NULL;

DMatch* DMatch::New() const {
  return new DMatch;
}

void DMatch::Clear() {
  if (_has_bits_[0 / 32] & (0xffu << (0 % 32))) {
    distance_ = 0u;
    trainidx_ = GOOGLE_ULONGLONG(0);
  }
  ::memset(_has_bits_, 0, sizeof(_has_bits_));
  mutable_unknown_fields()->Clear();
}

bool DMatch::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!(EXPRESSION)) return false
  ::google::protobuf::uint32 tag;
  while ((tag = input->ReadTag()) != 0) {
    switch (::google::protobuf::internal::WireFormatLite::GetTagFieldNumber(tag)) {
      // required uint32 distance = 1;
      case 1: {
        if (::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
            ::google::protobuf::internal::WireFormatLite::WIRETYPE_VARINT) {
          DO_((::google::protobuf::internal::WireFormatLite::ReadPrimitive<
                   ::google::protobuf::uint32, ::google::protobuf::internal::WireFormatLite::TYPE_UINT32>(
                 input, &distance_)));
          set_has_distance();
        } else {
          goto handle_uninterpreted;
        }
        if (input->ExpectTag(16)) goto parse_trainIdx;
        break;
      }
      
      // required uint64 trainIdx = 2;
      case 2: {
        if (::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
            ::google::protobuf::internal::WireFormatLite::WIRETYPE_VARINT) {
         parse_trainIdx:
          DO_((::google::protobuf::internal::WireFormatLite::ReadPrimitive<
                   ::google::protobuf::uint64, ::google::protobuf::internal::WireFormatLite::TYPE_UINT64>(
                 input, &trainidx_)));
          set_has_trainidx();
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

void DMatch::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // required uint32 distance = 1;
  if (has_distance()) {
    ::google::protobuf::internal::WireFormatLite::WriteUInt32(1, this->distance(), output);
  }
  
  // required uint64 trainIdx = 2;
  if (has_trainidx()) {
    ::google::protobuf::internal::WireFormatLite::WriteUInt64(2, this->trainidx(), output);
  }
  
  if (!unknown_fields().empty()) {
    ::google::protobuf::internal::WireFormat::SerializeUnknownFields(
        unknown_fields(), output);
  }
}

::google::protobuf::uint8* DMatch::SerializeWithCachedSizesToArray(
    ::google::protobuf::uint8* target) const {
  // required uint32 distance = 1;
  if (has_distance()) {
    target = ::google::protobuf::internal::WireFormatLite::WriteUInt32ToArray(1, this->distance(), target);
  }
  
  // required uint64 trainIdx = 2;
  if (has_trainidx()) {
    target = ::google::protobuf::internal::WireFormatLite::WriteUInt64ToArray(2, this->trainidx(), target);
  }
  
  if (!unknown_fields().empty()) {
    target = ::google::protobuf::internal::WireFormat::SerializeUnknownFieldsToArray(
        unknown_fields(), target);
  }
  return target;
}

int DMatch::ByteSize() const {
  int total_size = 0;
  
  if (_has_bits_[0 / 32] & (0xffu << (0 % 32))) {
    // required uint32 distance = 1;
    if (has_distance()) {
      total_size += 1 +
        ::google::protobuf::internal::WireFormatLite::UInt32Size(
          this->distance());
    }
    
    // required uint64 trainIdx = 2;
    if (has_trainidx()) {
      total_size += 1 +
        ::google::protobuf::internal::WireFormatLite::UInt64Size(
          this->trainidx());
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

void DMatch::MergeFrom(const ::google::protobuf::Message& from) {
  GOOGLE_CHECK_NE(&from, this);
  const DMatch* source =
    ::google::protobuf::internal::dynamic_cast_if_available<const DMatch*>(
      &from);
  if (source == NULL) {
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
    MergeFrom(*source);
  }
}

void DMatch::MergeFrom(const DMatch& from) {
  GOOGLE_CHECK_NE(&from, this);
  if (from._has_bits_[0 / 32] & (0xffu << (0 % 32))) {
    if (from.has_distance()) {
      set_distance(from.distance());
    }
    if (from.has_trainidx()) {
      set_trainidx(from.trainidx());
    }
  }
  mutable_unknown_fields()->MergeFrom(from.unknown_fields());
}

void DMatch::CopyFrom(const ::google::protobuf::Message& from) {
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void DMatch::CopyFrom(const DMatch& from) {
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

bool DMatch::IsInitialized() const {
  if ((_has_bits_[0] & 0x00000003) != 0x00000003) return false;
  
  return true;
}

void DMatch::Swap(DMatch* other) {
  if (other != this) {
    std::swap(distance_, other->distance_);
    std::swap(trainidx_, other->trainidx_);
    std::swap(_has_bits_[0], other->_has_bits_[0]);
    _unknown_fields_.Swap(&other->_unknown_fields_);
    std::swap(_cached_size_, other->_cached_size_);
  }
}

::google::protobuf::Metadata DMatch::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = DMatch_descriptor_;
  metadata.reflection = DMatch_reflection_;
  return metadata;
}


// @@protoc_insertion_point(namespace_scope)

}  // namespace proto
}  // namespace sas04225
}  // namespace org

// @@protoc_insertion_point(global_scope)
