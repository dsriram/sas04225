// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: DescriptorSetBuilderResult.proto

#ifndef PROTOBUF_DescriptorSetBuilderResult_2eproto__INCLUDED
#define PROTOBUF_DescriptorSetBuilderResult_2eproto__INCLUDED

#include <string>

#include <google/protobuf/stubs/common.h>

#if GOOGLE_PROTOBUF_VERSION < 2004000
#error This file was generated by a newer version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please update
#error your headers.
#endif
#if 2004001 < GOOGLE_PROTOBUF_MIN_PROTOC_VERSION
#error This file was generated by an older version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please
#error regenerate this file with a newer version of protoc.
#endif

#include <google/protobuf/generated_message_util.h>
#include <google/protobuf/repeated_field.h>
#include <google/protobuf/extension_set.h>
#include <google/protobuf/generated_message_reflection.h>
// @@protoc_insertion_point(includes)

namespace org {
namespace sas04225 {
namespace proto {

// Internal implementation detail -- do not call these.
void  protobuf_AddDesc_DescriptorSetBuilderResult_2eproto();
void protobuf_AssignDesc_DescriptorSetBuilderResult_2eproto();
void protobuf_ShutdownFile_DescriptorSetBuilderResult_2eproto();

class DescriptorSetBuilderResult;

// ===================================================================

class DescriptorSetBuilderResult : public ::google::protobuf::Message {
 public:
  DescriptorSetBuilderResult();
  virtual ~DescriptorSetBuilderResult();
  
  DescriptorSetBuilderResult(const DescriptorSetBuilderResult& from);
  
  inline DescriptorSetBuilderResult& operator=(const DescriptorSetBuilderResult& from) {
    CopyFrom(from);
    return *this;
  }
  
  inline const ::google::protobuf::UnknownFieldSet& unknown_fields() const {
    return _unknown_fields_;
  }
  
  inline ::google::protobuf::UnknownFieldSet* mutable_unknown_fields() {
    return &_unknown_fields_;
  }
  
  static const ::google::protobuf::Descriptor* descriptor();
  static const DescriptorSetBuilderResult& default_instance();
  
  void Swap(DescriptorSetBuilderResult* other);
  
  // implements Message ----------------------------------------------
  
  DescriptorSetBuilderResult* New() const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const DescriptorSetBuilderResult& from);
  void MergeFrom(const DescriptorSetBuilderResult& from);
  void Clear();
  bool IsInitialized() const;
  
  int ByteSize() const;
  bool MergePartialFromCodedStream(
      ::google::protobuf::io::CodedInputStream* input);
  void SerializeWithCachedSizes(
      ::google::protobuf::io::CodedOutputStream* output) const;
  ::google::protobuf::uint8* SerializeWithCachedSizesToArray(::google::protobuf::uint8* output) const;
  int GetCachedSize() const { return _cached_size_; }
  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const;
  public:
  
  ::google::protobuf::Metadata GetMetadata() const;
  
  // nested types ----------------------------------------------------
  
  // accessors -------------------------------------------------------
  
  // required string group_name = 1;
  inline bool has_group_name() const;
  inline void clear_group_name();
  static const int kGroupNameFieldNumber = 1;
  inline const ::std::string& group_name() const;
  inline void set_group_name(const ::std::string& value);
  inline void set_group_name(const char* value);
  inline void set_group_name(const char* value, size_t size);
  inline ::std::string* mutable_group_name();
  inline ::std::string* release_group_name();
  
  // required uint32 descriptor_count = 2;
  inline bool has_descriptor_count() const;
  inline void clear_descriptor_count();
  static const int kDescriptorCountFieldNumber = 2;
  inline ::google::protobuf::uint32 descriptor_count() const;
  inline void set_descriptor_count(::google::protobuf::uint32 value);
  
  // required uint64 startIndex = 3;
  inline bool has_startindex() const;
  inline void clear_startindex();
  static const int kStartIndexFieldNumber = 3;
  inline ::google::protobuf::uint64 startindex() const;
  inline void set_startindex(::google::protobuf::uint64 value);
  
  // required uint64 endIndex = 4;
  inline bool has_endindex() const;
  inline void clear_endindex();
  static const int kEndIndexFieldNumber = 4;
  inline ::google::protobuf::uint64 endindex() const;
  inline void set_endindex(::google::protobuf::uint64 value);
  
  // @@protoc_insertion_point(class_scope:org.sas04225.proto.DescriptorSetBuilderResult)
 private:
  inline void set_has_group_name();
  inline void clear_has_group_name();
  inline void set_has_descriptor_count();
  inline void clear_has_descriptor_count();
  inline void set_has_startindex();
  inline void clear_has_startindex();
  inline void set_has_endindex();
  inline void clear_has_endindex();
  
  ::google::protobuf::UnknownFieldSet _unknown_fields_;
  
  ::std::string* group_name_;
  ::google::protobuf::uint64 startindex_;
  ::google::protobuf::uint64 endindex_;
  ::google::protobuf::uint32 descriptor_count_;
  
  mutable int _cached_size_;
  ::google::protobuf::uint32 _has_bits_[(4 + 31) / 32];
  
  friend void  protobuf_AddDesc_DescriptorSetBuilderResult_2eproto();
  friend void protobuf_AssignDesc_DescriptorSetBuilderResult_2eproto();
  friend void protobuf_ShutdownFile_DescriptorSetBuilderResult_2eproto();
  
  void InitAsDefaultInstance();
  static DescriptorSetBuilderResult* default_instance_;
};
// ===================================================================


// ===================================================================

// DescriptorSetBuilderResult

// required string group_name = 1;
inline bool DescriptorSetBuilderResult::has_group_name() const {
  return (_has_bits_[0] & 0x00000001u) != 0;
}
inline void DescriptorSetBuilderResult::set_has_group_name() {
  _has_bits_[0] |= 0x00000001u;
}
inline void DescriptorSetBuilderResult::clear_has_group_name() {
  _has_bits_[0] &= ~0x00000001u;
}
inline void DescriptorSetBuilderResult::clear_group_name() {
  if (group_name_ != &::google::protobuf::internal::kEmptyString) {
    group_name_->clear();
  }
  clear_has_group_name();
}
inline const ::std::string& DescriptorSetBuilderResult::group_name() const {
  return *group_name_;
}
inline void DescriptorSetBuilderResult::set_group_name(const ::std::string& value) {
  set_has_group_name();
  if (group_name_ == &::google::protobuf::internal::kEmptyString) {
    group_name_ = new ::std::string;
  }
  group_name_->assign(value);
}
inline void DescriptorSetBuilderResult::set_group_name(const char* value) {
  set_has_group_name();
  if (group_name_ == &::google::protobuf::internal::kEmptyString) {
    group_name_ = new ::std::string;
  }
  group_name_->assign(value);
}
inline void DescriptorSetBuilderResult::set_group_name(const char* value, size_t size) {
  set_has_group_name();
  if (group_name_ == &::google::protobuf::internal::kEmptyString) {
    group_name_ = new ::std::string;
  }
  group_name_->assign(reinterpret_cast<const char*>(value), size);
}
inline ::std::string* DescriptorSetBuilderResult::mutable_group_name() {
  set_has_group_name();
  if (group_name_ == &::google::protobuf::internal::kEmptyString) {
    group_name_ = new ::std::string;
  }
  return group_name_;
}
inline ::std::string* DescriptorSetBuilderResult::release_group_name() {
  clear_has_group_name();
  if (group_name_ == &::google::protobuf::internal::kEmptyString) {
    return NULL;
  } else {
    ::std::string* temp = group_name_;
    group_name_ = const_cast< ::std::string*>(&::google::protobuf::internal::kEmptyString);
    return temp;
  }
}

// required uint32 descriptor_count = 2;
inline bool DescriptorSetBuilderResult::has_descriptor_count() const {
  return (_has_bits_[0] & 0x00000002u) != 0;
}
inline void DescriptorSetBuilderResult::set_has_descriptor_count() {
  _has_bits_[0] |= 0x00000002u;
}
inline void DescriptorSetBuilderResult::clear_has_descriptor_count() {
  _has_bits_[0] &= ~0x00000002u;
}
inline void DescriptorSetBuilderResult::clear_descriptor_count() {
  descriptor_count_ = 0u;
  clear_has_descriptor_count();
}
inline ::google::protobuf::uint32 DescriptorSetBuilderResult::descriptor_count() const {
  return descriptor_count_;
}
inline void DescriptorSetBuilderResult::set_descriptor_count(::google::protobuf::uint32 value) {
  set_has_descriptor_count();
  descriptor_count_ = value;
}

// required uint64 startIndex = 3;
inline bool DescriptorSetBuilderResult::has_startindex() const {
  return (_has_bits_[0] & 0x00000004u) != 0;
}
inline void DescriptorSetBuilderResult::set_has_startindex() {
  _has_bits_[0] |= 0x00000004u;
}
inline void DescriptorSetBuilderResult::clear_has_startindex() {
  _has_bits_[0] &= ~0x00000004u;
}
inline void DescriptorSetBuilderResult::clear_startindex() {
  startindex_ = GOOGLE_ULONGLONG(0);
  clear_has_startindex();
}
inline ::google::protobuf::uint64 DescriptorSetBuilderResult::startindex() const {
  return startindex_;
}
inline void DescriptorSetBuilderResult::set_startindex(::google::protobuf::uint64 value) {
  set_has_startindex();
  startindex_ = value;
}

// required uint64 endIndex = 4;
inline bool DescriptorSetBuilderResult::has_endindex() const {
  return (_has_bits_[0] & 0x00000008u) != 0;
}
inline void DescriptorSetBuilderResult::set_has_endindex() {
  _has_bits_[0] |= 0x00000008u;
}
inline void DescriptorSetBuilderResult::clear_has_endindex() {
  _has_bits_[0] &= ~0x00000008u;
}
inline void DescriptorSetBuilderResult::clear_endindex() {
  endindex_ = GOOGLE_ULONGLONG(0);
  clear_has_endindex();
}
inline ::google::protobuf::uint64 DescriptorSetBuilderResult::endindex() const {
  return endindex_;
}
inline void DescriptorSetBuilderResult::set_endindex(::google::protobuf::uint64 value) {
  set_has_endindex();
  endindex_ = value;
}


// @@protoc_insertion_point(namespace_scope)

}  // namespace proto
}  // namespace sas04225
}  // namespace org

#ifndef SWIG
namespace google {
namespace protobuf {


}  // namespace google
}  // namespace protobuf
#endif  // SWIG

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_DescriptorSetBuilderResult_2eproto__INCLUDED