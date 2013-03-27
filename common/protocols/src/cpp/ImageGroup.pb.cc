// Generated by the protocol buffer compiler.  DO NOT EDIT!

#define INTERNAL_SUPPRESS_PROTOBUF_FIELD_DEPRECATION
#include "ImageGroup.pb.h"

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

const ::google::protobuf::Descriptor* ImageGroup_descriptor_ = NULL;
const ::google::protobuf::internal::GeneratedMessageReflection*
  ImageGroup_reflection_ = NULL;

}  // namespace


void protobuf_AssignDesc_ImageGroup_2eproto() {
  protobuf_AddDesc_ImageGroup_2eproto();
  const ::google::protobuf::FileDescriptor* file =
    ::google::protobuf::DescriptorPool::generated_pool()->FindFileByName(
      "ImageGroup.proto");
  GOOGLE_CHECK(file != NULL);
  ImageGroup_descriptor_ = file->message_type(0);
  static const int ImageGroup_offsets_[2] = {
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(ImageGroup, group_name_),
    GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(ImageGroup, images_),
  };
  ImageGroup_reflection_ =
    new ::google::protobuf::internal::GeneratedMessageReflection(
      ImageGroup_descriptor_,
      ImageGroup::default_instance_,
      ImageGroup_offsets_,
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(ImageGroup, _has_bits_[0]),
      GOOGLE_PROTOBUF_GENERATED_MESSAGE_FIELD_OFFSET(ImageGroup, _unknown_fields_),
      -1,
      ::google::protobuf::DescriptorPool::generated_pool(),
      ::google::protobuf::MessageFactory::generated_factory(),
      sizeof(ImageGroup));
}

namespace {

GOOGLE_PROTOBUF_DECLARE_ONCE(protobuf_AssignDescriptors_once_);
inline void protobuf_AssignDescriptorsOnce() {
  ::google::protobuf::GoogleOnceInit(&protobuf_AssignDescriptors_once_,
                 &protobuf_AssignDesc_ImageGroup_2eproto);
}

void protobuf_RegisterTypes(const ::std::string&) {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedMessage(
    ImageGroup_descriptor_, &ImageGroup::default_instance());
}

}  // namespace

void protobuf_ShutdownFile_ImageGroup_2eproto() {
  delete ImageGroup::default_instance_;
  delete ImageGroup_reflection_;
}

void protobuf_AddDesc_ImageGroup_2eproto() {
  static bool already_here = false;
  if (already_here) return;
  already_here = true;
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  ::google::protobuf::DescriptorPool::InternalAddGeneratedFile(
    "\n\020ImageGroup.proto\022\022org.sas04225.proto\"0"
    "\n\nImageGroup\022\022\n\ngroup_name\030\001 \002(\t\022\016\n\006imag"
    "es\030\002 \003(\tB%\n\022org.sas04225.protoB\017ImageGro"
    "upProto", 127);
  ::google::protobuf::MessageFactory::InternalRegisterGeneratedFile(
    "ImageGroup.proto", &protobuf_RegisterTypes);
  ImageGroup::default_instance_ = new ImageGroup();
  ImageGroup::default_instance_->InitAsDefaultInstance();
  ::google::protobuf::internal::OnShutdown(&protobuf_ShutdownFile_ImageGroup_2eproto);
}

// Force AddDescriptors() to be called at static initialization time.
struct StaticDescriptorInitializer_ImageGroup_2eproto {
  StaticDescriptorInitializer_ImageGroup_2eproto() {
    protobuf_AddDesc_ImageGroup_2eproto();
  }
} static_descriptor_initializer_ImageGroup_2eproto_;


// ===================================================================

#ifndef _MSC_VER
const int ImageGroup::kGroupNameFieldNumber;
const int ImageGroup::kImagesFieldNumber;
#endif  // !_MSC_VER

ImageGroup::ImageGroup()
  : ::google::protobuf::Message() {
  SharedCtor();
}

void ImageGroup::InitAsDefaultInstance() {
}

ImageGroup::ImageGroup(const ImageGroup& from)
  : ::google::protobuf::Message() {
  SharedCtor();
  MergeFrom(from);
}

void ImageGroup::SharedCtor() {
  _cached_size_ = 0;
  group_name_ = const_cast< ::std::string*>(&::google::protobuf::internal::kEmptyString);
  ::memset(_has_bits_, 0, sizeof(_has_bits_));
}

ImageGroup::~ImageGroup() {
  SharedDtor();
}

void ImageGroup::SharedDtor() {
  if (group_name_ != &::google::protobuf::internal::kEmptyString) {
    delete group_name_;
  }
  if (this != default_instance_) {
  }
}

void ImageGroup::SetCachedSize(int size) const {
  GOOGLE_SAFE_CONCURRENT_WRITES_BEGIN();
  _cached_size_ = size;
  GOOGLE_SAFE_CONCURRENT_WRITES_END();
}
const ::google::protobuf::Descriptor* ImageGroup::descriptor() {
  protobuf_AssignDescriptorsOnce();
  return ImageGroup_descriptor_;
}

const ImageGroup& ImageGroup::default_instance() {
  if (default_instance_ == NULL) protobuf_AddDesc_ImageGroup_2eproto();  return *default_instance_;
}

ImageGroup* ImageGroup::default_instance_ = NULL;

ImageGroup* ImageGroup::New() const {
  return new ImageGroup;
}

void ImageGroup::Clear() {
  if (_has_bits_[0 / 32] & (0xffu << (0 % 32))) {
    if (has_group_name()) {
      if (group_name_ != &::google::protobuf::internal::kEmptyString) {
        group_name_->clear();
      }
    }
  }
  images_.Clear();
  ::memset(_has_bits_, 0, sizeof(_has_bits_));
  mutable_unknown_fields()->Clear();
}

bool ImageGroup::MergePartialFromCodedStream(
    ::google::protobuf::io::CodedInputStream* input) {
#define DO_(EXPRESSION) if (!(EXPRESSION)) return false
  ::google::protobuf::uint32 tag;
  while ((tag = input->ReadTag()) != 0) {
    switch (::google::protobuf::internal::WireFormatLite::GetTagFieldNumber(tag)) {
      // required string group_name = 1;
      case 1: {
        if (::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
            ::google::protobuf::internal::WireFormatLite::WIRETYPE_LENGTH_DELIMITED) {
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->mutable_group_name()));
          ::google::protobuf::internal::WireFormat::VerifyUTF8String(
            this->group_name().data(), this->group_name().length(),
            ::google::protobuf::internal::WireFormat::PARSE);
        } else {
          goto handle_uninterpreted;
        }
        if (input->ExpectTag(18)) goto parse_images;
        break;
      }
      
      // repeated string images = 2;
      case 2: {
        if (::google::protobuf::internal::WireFormatLite::GetTagWireType(tag) ==
            ::google::protobuf::internal::WireFormatLite::WIRETYPE_LENGTH_DELIMITED) {
         parse_images:
          DO_(::google::protobuf::internal::WireFormatLite::ReadString(
                input, this->add_images()));
          ::google::protobuf::internal::WireFormat::VerifyUTF8String(
            this->images(0).data(), this->images(0).length(),
            ::google::protobuf::internal::WireFormat::PARSE);
        } else {
          goto handle_uninterpreted;
        }
        if (input->ExpectTag(18)) goto parse_images;
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

void ImageGroup::SerializeWithCachedSizes(
    ::google::protobuf::io::CodedOutputStream* output) const {
  // required string group_name = 1;
  if (has_group_name()) {
    ::google::protobuf::internal::WireFormat::VerifyUTF8String(
      this->group_name().data(), this->group_name().length(),
      ::google::protobuf::internal::WireFormat::SERIALIZE);
    ::google::protobuf::internal::WireFormatLite::WriteString(
      1, this->group_name(), output);
  }
  
  // repeated string images = 2;
  for (int i = 0; i < this->images_size(); i++) {
  ::google::protobuf::internal::WireFormat::VerifyUTF8String(
    this->images(i).data(), this->images(i).length(),
    ::google::protobuf::internal::WireFormat::SERIALIZE);
    ::google::protobuf::internal::WireFormatLite::WriteString(
      2, this->images(i), output);
  }
  
  if (!unknown_fields().empty()) {
    ::google::protobuf::internal::WireFormat::SerializeUnknownFields(
        unknown_fields(), output);
  }
}

::google::protobuf::uint8* ImageGroup::SerializeWithCachedSizesToArray(
    ::google::protobuf::uint8* target) const {
  // required string group_name = 1;
  if (has_group_name()) {
    ::google::protobuf::internal::WireFormat::VerifyUTF8String(
      this->group_name().data(), this->group_name().length(),
      ::google::protobuf::internal::WireFormat::SERIALIZE);
    target =
      ::google::protobuf::internal::WireFormatLite::WriteStringToArray(
        1, this->group_name(), target);
  }
  
  // repeated string images = 2;
  for (int i = 0; i < this->images_size(); i++) {
    ::google::protobuf::internal::WireFormat::VerifyUTF8String(
      this->images(i).data(), this->images(i).length(),
      ::google::protobuf::internal::WireFormat::SERIALIZE);
    target = ::google::protobuf::internal::WireFormatLite::
      WriteStringToArray(2, this->images(i), target);
  }
  
  if (!unknown_fields().empty()) {
    target = ::google::protobuf::internal::WireFormat::SerializeUnknownFieldsToArray(
        unknown_fields(), target);
  }
  return target;
}

int ImageGroup::ByteSize() const {
  int total_size = 0;
  
  if (_has_bits_[0 / 32] & (0xffu << (0 % 32))) {
    // required string group_name = 1;
    if (has_group_name()) {
      total_size += 1 +
        ::google::protobuf::internal::WireFormatLite::StringSize(
          this->group_name());
    }
    
  }
  // repeated string images = 2;
  total_size += 1 * this->images_size();
  for (int i = 0; i < this->images_size(); i++) {
    total_size += ::google::protobuf::internal::WireFormatLite::StringSize(
      this->images(i));
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

void ImageGroup::MergeFrom(const ::google::protobuf::Message& from) {
  GOOGLE_CHECK_NE(&from, this);
  const ImageGroup* source =
    ::google::protobuf::internal::dynamic_cast_if_available<const ImageGroup*>(
      &from);
  if (source == NULL) {
    ::google::protobuf::internal::ReflectionOps::Merge(from, this);
  } else {
    MergeFrom(*source);
  }
}

void ImageGroup::MergeFrom(const ImageGroup& from) {
  GOOGLE_CHECK_NE(&from, this);
  images_.MergeFrom(from.images_);
  if (from._has_bits_[0 / 32] & (0xffu << (0 % 32))) {
    if (from.has_group_name()) {
      set_group_name(from.group_name());
    }
  }
  mutable_unknown_fields()->MergeFrom(from.unknown_fields());
}

void ImageGroup::CopyFrom(const ::google::protobuf::Message& from) {
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void ImageGroup::CopyFrom(const ImageGroup& from) {
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

bool ImageGroup::IsInitialized() const {
  if ((_has_bits_[0] & 0x00000001) != 0x00000001) return false;
  
  return true;
}

void ImageGroup::Swap(ImageGroup* other) {
  if (other != this) {
    std::swap(group_name_, other->group_name_);
    images_.Swap(&other->images_);
    std::swap(_has_bits_[0], other->_has_bits_[0]);
    _unknown_fields_.Swap(&other->_unknown_fields_);
    std::swap(_cached_size_, other->_cached_size_);
  }
}

::google::protobuf::Metadata ImageGroup::GetMetadata() const {
  protobuf_AssignDescriptorsOnce();
  ::google::protobuf::Metadata metadata;
  metadata.descriptor = ImageGroup_descriptor_;
  metadata.reflection = ImageGroup_reflection_;
  return metadata;
}


// @@protoc_insertion_point(namespace_scope)

}  // namespace proto
}  // namespace sas04225
}  // namespace org

// @@protoc_insertion_point(global_scope)
