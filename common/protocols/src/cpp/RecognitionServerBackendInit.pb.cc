// Generated by the protocol buffer compiler.  DO NOT EDIT!

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "RecognitionServerBackendInit.pb.h"

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

const ::google::protobuf::Descriptor* RecognitionServerBackendInit_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  RecognitionServerBackendInit_reflection_ = NULL;

}  // namespace


void protobuf_AssignDesc_RecognitionServerBackendInit_2eproto() {
  protobuf_AddDesc_RecognitionServerBackendInit_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "RecognitionServerBackendInit.proto");
  GOOGLE_CHECK(file != NULL);
  RecognitionServerBackendInit_descriptor_ = file->message_type(0);
  static const int RecognitionServerBackendInit_offsets_[2] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(RecognitionServerBackendInit, descriptor_count_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(RecognitionServerBackendInit, cache_path_),
  };
  RecognitionServerBackendInit_reflection_ =
    new ::google::protobuf::internal::GeneratedMessageReflection(
      RecognitionServerBackendInit_descriptor_,
      RecognitionServerBackendInit::default_instance_,
      RecognitionServerBackendInit_offsets_,
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(RecognitionServerBackendInit, _has_bits_[0]),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(RecognitionServerBackendInit, _unknown_fields_),
      -1,
      ::google::protobuf::DescriptorPool::generated_pool(),
      ::google::protobuf::MessageFactory::generated_factory(),
      sizeof(RecognitionServerBackendInit));
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
inline void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_RecognitionServerBackendInit_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
    RecognitionServerBackendInit_descriptor_, &RecognitionServerBackendInit::default_instance());
}

}  // namespace

void protobuf_ShutdownFile_RecognitionServerBackendInit_2eproto() {
  delete RecognitionServerBackendInit::default_instance_;
  delete RecognitionServerBackendInit_reflection_;
}

void protobuf_AddDesc_RecognitionServerBackendInit_2eproto() {
  static bool already_here = false;
  if (already_here) return;
  already_here = true;
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n\"RecognitionServerBackendInit.proto\022\022or"
    "g.sas04225.proto\"L\n\034RecognitionServerBac"
    "kendInit\022\030\n\020descriptor_count\030\001 \002(\004\022\022\n\nca"
    "che_path\030\002 \002(\tB7\n\022org.sas04225.protoB!Re"
    "cognitionServerBackendInitProto", 191);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "RecognitionServerBackendInit.proto", &protobuf_RegisterTypes);
  RecognitionServerBackendInit::default_instance_ = new RecognitionServerBackendInit();
  RecognitionServerBackendInit::default_instance_->InitAsDefaultInstance();
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_RecognitionServerBackendInit_2eproto);
}

// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_RecognitionServerBackendInit_2eproto {
  StaticDescriptorInitializer_RecognitionServerBackendInit_2eproto() {
    protobuf_AddDesc_RecognitionServerBackendInit_2eproto();
  }
} static_descriptor_initializer_RecognitionServerBackendInit_2eproto_;


// ===================================================================

#ifndef _MSC_VER
const int RecognitionServerBackendInit::kDescriptorCountFieldNumber;
const int RecognitionServerBackendInit::kCachePathFieldNumber;
#endif  // !_MSC_VER

RecognitionServerBackendInit::RecognitionServerBackendInit()
  : ::google::protobuf::Message() {
  SharedCtor();
}

void RecognitionServerBackendInit::InitAsDefaultInstance() {
}

RecognitionServerBackendInit::RecognitionServerBackendInit(const RecognitionServerBackendInit& from)
  : ::google::protobuf::Message() {
  SharedCtor();
  MergeFrom(from);
}

void RecognitionServerBackendInit::SharedCtor() {
  _cached_size_ = 0;
  descriptor_count_ = GOOGLE_ULONGLONG(0);
  cache_path_ = const_cast< ::std::string*>(&::google::protobuf::internal::kEmptyString);
  ::memset(_has_bits_, 0, sizeof(_has_bits_));
}

RecognitionServerBackendInit::~RecognitionServerBackendInit() {
  SharedDtor();
}

void RecognitionServerBackendInit::SharedDtor() {
  if (cache_path_ != &::google::protobuf::internal::kEmptyString) {
    delete cache_path_;
  }
  if (this != default_instance_) {
  }
}

void RecognitionServerBackendInit::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* RecognitionServerBackendInit::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return RecognitionServerBackendInit_descriptor_;
}

const RecognitionServerBackendInit& RecognitionServerBackendInit::default_instance() {
  if (default_instance_ == NULL) protobuf_AddDesc_RecognitionServerBackendInit_2eproto();  return *default_instance_;
}

RecognitionServerBackendInit* RecognitionServerBackendInit::default_instance_ = NULL;

RecognitionServerBackendInit* RecognitionServerBackendInit::New() const {
  return new RecognitionServerBackendInit;
}

void RecognitionServerBackendInit::Clear() {
  if (_has_bits_[0 / 32] & (0xffu << (0 % 32))) {
    descriptor_count_ = GOOGLE_ULONGLONG(0);
    if (has_cache_path()) {
      if (cache_path_ != &::google::protobuf::internal::kEmptyString) {
        cache_path_->clear();
      }
    }
  }
  ::memset(_has_bits_, 0, sizeof(_has_bits_));
  mutable_unknown_fields()->Clear();
}

bool RecognitionServerBackendInit::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!(EXPRESSION)) return false
  ::google::protobuf::uint32 tag;
  while ((tag = input->ReadTag()) != 0) {
    switch (::google::protobuf::internal::WireFormatLite::GetTagFieldNumber(tag)) {
      // required uint64 descriptor_count = 1;
      case 1: {
        if (::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
            ::google::protobuf::internal::WireFormatLite::WIRETYPE_VARINT) {
          DO_((::google::protobuf::internal::WireFormatLite::ReadPrimitive<
                   ::google::protobuf::uint64, ::google::protobuf::internal::WireFormatLite::TYPE_UINT64>(
                 input, &descriptor_count_)));
          set_has_descriptor_count();
        } else {
          goto handle_uninterpreted;
        }
        if (input->ExpectTag(18)) goto parse_cache_path;
        break;
      }
      
      // required string cache_path = 2;
      case 2: {
        if (::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
            ::google::protobuf::internal::WireFormatLite::WIRETYPE_LENGTH_DELIMITED) {
         parse_cache_path:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_cache_path()));
          ::google::protobuf::internal::WireFormat::VerifyUTF8String(
            this->cache_path().data(), this->cache_path().length(),
            ::google::protobuf::internal::WireFormat::PARSE);
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

void RecognitionServerBackendInit::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // required uint64 descriptor_count = 1;
  if (has_descriptor_count()) {
    ::google::protobuf::internal::WireFormatLite::WriteUInt64(1, this->descriptor_count(), output);
  }
  
  // required string cache_path = 2;
  if (has_cache_path()) {
    ::google::protobuf::internal::WireFormat::VerifyUTF8String(
      this->cache_path().data(), this->cache_path().length(),
      ::google::protobuf::internal::WireFormat::SERIALIZE);
    ::google::protobuf::internal::WireFormatLite::WriteString(
      2, this->cache_path(), output);
  }
  
  if (!unknown_fields().empty()) {
    ::google::protobuf::internal::WireFormat::SerializeUnknownFields(
        unknown_fields(), output);
  }
}

::google::protobuf::uint8* RecognitionServerBackendInit::SerializeWithCachedSizesToArray(
    ::google::protobuf::uint8* target) const {
  // required uint64 descriptor_count = 1;
  if (has_descriptor_count()) {
    target = ::google::protobuf::internal::WireFormatLite::WriteUInt64ToArray(1, this->descriptor_count(), target);
  }
  
  // required string cache_path = 2;
  if (has_cache_path()) {
    ::google::protobuf::internal::WireFormat::VerifyUTF8String(
      this->cache_path().data(), this->cache_path().length(),
      ::google::protobuf::internal::WireFormat::SERIALIZE);
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        2, this->cache_path(), target);
  }
  
  if (!unknown_fields().empty()) {
    target = ::google::protobuf::internal::WireFormat::SerializeUnknownFieldsToArray(
        unknown_fields(), target);
  }
  return target;
}

int RecognitionServerBackendInit::ByteSize() const {
  int total_size = 0;
  
  if (_has_bits_[0 / 32] & (0xffu << (0 % 32))) {
    // required uint64 descriptor_count = 1;
    if (has_descriptor_count()) {
      total_size += 1 +
        ::google::protobuf::internal::WireFormatLite::UInt64Size(
          this->descriptor_count());
    }
    
    // required string cache_path = 2;
    if (has_cache_path()) {
      total_size += 1 +
        ::google::protobuf::internal::WireFormatLite::StringSize(
          this->cache_path());
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

void RecognitionServerBackendInit::MergeFrom(const ::google::protobuf::Message& from) {
  GOOGLE_CHECK_NE(&from, this);
  const RecognitionServerBackendInit* source =
    ::google::protobuf::internal::dynamic_cast_if_available<const RecognitionServerBackendInit*>(
      &from);
  if (source == NULL) {
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
    MergeFrom(*source);
  }
}

void RecognitionServerBackendInit::MergeFrom(const RecognitionServerBackendInit& from) {
  GOOGLE_CHECK_NE(&from, this);
  if (from._has_bits_[0 / 32] & (0xffu << (0 % 32))) {
    if (from.has_descriptor_count()) {
      set_descriptor_count(from.descriptor_count());
    }
    if (from.has_cache_path()) {
      set_cache_path(from.cache_path());
    }
  }
  mutable_unknown_fields()->MergeFrom(from.unknown_fields());
}

void RecognitionServerBackendInit::CopyFrom(const ::google::protobuf::Message& from) {
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void RecognitionServerBackendInit::CopyFrom(const RecognitionServerBackendInit& from) {
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

bool RecognitionServerBackendInit::IsInitialized() const {
  if ((_has_bits_[0] & 0x00000003) != 0x00000003) return false;
  
  return true;
}

void RecognitionServerBackendInit::Swap(RecognitionServerBackendInit* other) {
  if (other != this) {
    std::swap(descriptor_count_, other->descriptor_count_);
    std::swap(cache_path_, other->cache_path_);
    std::swap(_has_bits_[0], other->_has_bits_[0]);
    _unknown_fields_.Swap(&other->_unknown_fields_);
    std::swap(_cached_size_, other->_cached_size_);
  }
}

::google::protobuf::Metadata RecognitionServerBackendInit::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = RecognitionServerBackendInit_descriptor_;
  metadata.reflection = RecognitionServerBackendInit_reflection_;
  return metadata;
}


// @@protoc_insertion_point(namespace_scope)

}  // namespace proto
}  // namespace sas04225
}  // namespace org

// @@protoc_insertion_point(global_scope)