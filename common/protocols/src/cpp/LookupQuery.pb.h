// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: LookupQuery.proto

#ifndef PROTOBUF_LookupQuery_2eproto__INCLUDED
#define PROTOBUF_LookupQuery_2eproto__INCLUDED

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
void  protobuf_AddDesc_LookupQuery_2eproto();
void protobuf_AssignDesc_LookupQuery_2eproto();
void protobuf_ShutdownFile_LookupQuery_2eproto();

class LookupQuery;
class Descriptors;

// ===================================================================

class LookupQuery : public ::google::protobuf::Message {
 public:
  LookupQuery();
  virtual ~LookupQuery();
  
  LookupQuery(const LookupQuery& from);
  
  inline LookupQuery& operator=(const LookupQuery& from) {
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
  static const LookupQuery& default_instance();
  
  void Swap(LookupQuery* other);
  
  // implements Message ----------------------------------------------
  
  LookupQuery* New() const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const LookupQuery& from);
  void MergeFrom(const LookupQuery& from);
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
  
  // required uint64 request_id = 1;
  inline bool has_request_id() const;
  inline void clear_request_id();
  static const int kRequestIdFieldNumber = 1;
  inline ::google::protobuf::uint64 request_id() const;
  inline void set_request_id(::google::protobuf::uint64 value);
  
  // required string path = 2;
  inline bool has_path() const;
  inline void clear_path();
  static const int kPathFieldNumber = 2;
  inline const ::std::string& path() const;
  inline void set_path(const ::std::string& value);
  inline void set_path(const char* value);
  inline void set_path(const char* value, size_t size);
  inline ::std::string* mutable_path();
  inline ::std::string* release_path();
  
  // required .org.sas04225.proto.Descriptors descriptors = 3;
  inline bool has_descriptors() const;
  inline void clear_descriptors();
  static const int kDescriptorsFieldNumber = 3;
  inline const ::org::sas04225::proto::Descriptors& descriptors() const;
  inline ::org::sas04225::proto::Descriptors* mutable_descriptors();
  inline ::org::sas04225::proto::Descriptors* release_descriptors();
  
  // @@protoc_insertion_point(class_scope:org.sas04225.proto.LookupQuery)
 private:
  inline void set_has_request_id();
  inline void clear_has_request_id();
  inline void set_has_path();
  inline void clear_has_path();
  inline void set_has_descriptors();
  inline void clear_has_descriptors();
  
  ::google::protobuf::UnknownFieldSet _unknown_fields_;
  
  ::google::protobuf::uint64 request_id_;
  ::std::string* path_;
  ::org::sas04225::proto::Descriptors* descriptors_;
  
  mutable int _cached_size_;
  ::google::protobuf::uint32 _has_bits_[(3 + 31) / 32];
  
  friend void  protobuf_AddDesc_LookupQuery_2eproto();
  friend void protobuf_AssignDesc_LookupQuery_2eproto();
  friend void protobuf_ShutdownFile_LookupQuery_2eproto();
  
  void InitAsDefaultInstance();
  static LookupQuery* default_instance_;
};
// -------------------------------------------------------------------

class Descriptors : public ::google::protobuf::Message {
 public:
  Descriptors();
  virtual ~Descriptors();
  
  Descriptors(const Descriptors& from);
  
  inline Descriptors& operator=(const Descriptors& from) {
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
  static const Descriptors& default_instance();
  
  void Swap(Descriptors* other);
  
  // implements Message ----------------------------------------------
  
  Descriptors* New() const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const Descriptors& from);
  void MergeFrom(const Descriptors& from);
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
  
  // required uint32 descriptor_size = 1;
  inline bool has_descriptor_size() const;
  inline void clear_descriptor_size();
  static const int kDescriptorSizeFieldNumber = 1;
  inline ::google::protobuf::uint32 descriptor_size() const;
  inline void set_descriptor_size(::google::protobuf::uint32 value);
  
  // repeated bytes descriptors = 2;
  inline int descriptors_size() const;
  inline void clear_descriptors();
  static const int kDescriptorsFieldNumber = 2;
  inline const ::std::string& descriptors(int index) const;
  inline ::std::string* mutable_descriptors(int index);
  inline void set_descriptors(int index, const ::std::string& value);
  inline void set_descriptors(int index, const char* value);
  inline void set_descriptors(int index, const void* value, size_t size);
  inline ::std::string* add_descriptors();
  inline void add_descriptors(const ::std::string& value);
  inline void add_descriptors(const char* value);
  inline void add_descriptors(const void* value, size_t size);
  inline const ::google::protobuf::RepeatedPtrField< ::std::string>& descriptors() const;
  inline ::google::protobuf::RepeatedPtrField< ::std::string>* mutable_descriptors();
  
  // @@protoc_insertion_point(class_scope:org.sas04225.proto.Descriptors)
 private:
  inline void set_has_descriptor_size();
  inline void clear_has_descriptor_size();
  
  ::google::protobuf::UnknownFieldSet _unknown_fields_;
  
  ::google::protobuf::RepeatedPtrField< ::std::string> descriptors_;
  ::google::protobuf::uint32 descriptor_size_;
  
  mutable int _cached_size_;
  ::google::protobuf::uint32 _has_bits_[(2 + 31) / 32];
  
  friend void  protobuf_AddDesc_LookupQuery_2eproto();
  friend void protobuf_AssignDesc_LookupQuery_2eproto();
  friend void protobuf_ShutdownFile_LookupQuery_2eproto();
  
  void InitAsDefaultInstance();
  static Descriptors* default_instance_;
};
// ===================================================================


// ===================================================================

// LookupQuery

// required uint64 request_id = 1;
inline bool LookupQuery::has_request_id() const {
  return (_has_bits_[0] & 0x00000001u) != 0;
}
inline void LookupQuery::set_has_request_id() {
  _has_bits_[0] |= 0x00000001u;
}
inline void LookupQuery::clear_has_request_id() {
  _has_bits_[0] &= ~0x00000001u;
}
inline void LookupQuery::clear_request_id() {
  request_id_ = GOOGLE_ULONGLONG(0);
  clear_has_request_id();
}
inline ::google::protobuf::uint64 LookupQuery::request_id() const {
  return request_id_;
}
inline void LookupQuery::set_request_id(::google::protobuf::uint64 value) {
  set_has_request_id();
  request_id_ = value;
}

// required string path = 2;
inline bool LookupQuery::has_path() const {
  return (_has_bits_[0] & 0x00000002u) != 0;
}
inline void LookupQuery::set_has_path() {
  _has_bits_[0] |= 0x00000002u;
}
inline void LookupQuery::clear_has_path() {
  _has_bits_[0] &= ~0x00000002u;
}
inline void LookupQuery::clear_path() {
  if (path_ != &::google::protobuf::internal::kEmptyString) {
    path_->clear();
  }
  clear_has_path();
}
inline const ::std::string& LookupQuery::path() const {
  return *path_;
}
inline void LookupQuery::set_path(const ::std::string& value) {
  set_has_path();
  if (path_ == &::google::protobuf::internal::kEmptyString) {
    path_ = new ::std::string;
  }
  path_->assign(value);
}
inline void LookupQuery::set_path(const char* value) {
  set_has_path();
  if (path_ == &::google::protobuf::internal::kEmptyString) {
    path_ = new ::std::string;
  }
  path_->assign(value);
}
inline void LookupQuery::set_path(const char* value, size_t size) {
  set_has_path();
  if (path_ == &::google::protobuf::internal::kEmptyString) {
    path_ = new ::std::string;
  }
  path_->assign(reinterpret_cast<const char*>(value), size);
}
inline ::std::string* LookupQuery::mutable_path() {
  set_has_path();
  if (path_ == &::google::protobuf::internal::kEmptyString) {
    path_ = new ::std::string;
  }
  return path_;
}
inline ::std::string* LookupQuery::release_path() {
  clear_has_path();
  if (path_ == &::google::protobuf::internal::kEmptyString) {
    return NULL;
  } else {
    ::std::string* temp = path_;
    path_ = const_cast< ::std::string*>(&::google::protobuf::internal::kEmptyString);
    return temp;
  }
}

// required .org.sas04225.proto.Descriptors descriptors = 3;
inline bool LookupQuery::has_descriptors() const {
  return (_has_bits_[0] & 0x00000004u) != 0;
}
inline void LookupQuery::set_has_descriptors() {
  _has_bits_[0] |= 0x00000004u;
}
inline void LookupQuery::clear_has_descriptors() {
  _has_bits_[0] &= ~0x00000004u;
}
inline void LookupQuery::clear_descriptors() {
  if (descriptors_ != NULL) descriptors_->::org::sas04225::proto::Descriptors::Clear();
  clear_has_descriptors();
}
inline const ::org::sas04225::proto::Descriptors& LookupQuery::descriptors() const {
  return descriptors_ != NULL ? *descriptors_ : *default_instance_->descriptors_;
}
inline ::org::sas04225::proto::Descriptors* LookupQuery::mutable_descriptors() {
  set_has_descriptors();
  if (descriptors_ == NULL) descriptors_ = new ::org::sas04225::proto::Descriptors;
  return descriptors_;
}
inline ::org::sas04225::proto::Descriptors* LookupQuery::release_descriptors() {
  clear_has_descriptors();
  ::org::sas04225::proto::Descriptors* temp = descriptors_;
  descriptors_ = NULL;
  return temp;
}

// -------------------------------------------------------------------

// Descriptors

// required uint32 descriptor_size = 1;
inline bool Descriptors::has_descriptor_size() const {
  return (_has_bits_[0] & 0x00000001u) != 0;
}
inline void Descriptors::set_has_descriptor_size() {
  _has_bits_[0] |= 0x00000001u;
}
inline void Descriptors::clear_has_descriptor_size() {
  _has_bits_[0] &= ~0x00000001u;
}
inline void Descriptors::clear_descriptor_size() {
  descriptor_size_ = 0u;
  clear_has_descriptor_size();
}
inline ::google::protobuf::uint32 Descriptors::descriptor_size() const {
  return descriptor_size_;
}
inline void Descriptors::set_descriptor_size(::google::protobuf::uint32 value) {
  set_has_descriptor_size();
  descriptor_size_ = value;
}

// repeated bytes descriptors = 2;
inline int Descriptors::descriptors_size() const {
  return descriptors_.size();
}
inline void Descriptors::clear_descriptors() {
  descriptors_.Clear();
}
inline const ::std::string& Descriptors::descriptors(int index) const {
  return descriptors_.Get(index);
}
inline ::std::string* Descriptors::mutable_descriptors(int index) {
  return descriptors_.Mutable(index);
}
inline void Descriptors::set_descriptors(int index, const ::std::string& value) {
  descriptors_.Mutable(index)->assign(value);
}
inline void Descriptors::set_descriptors(int index, const char* value) {
  descriptors_.Mutable(index)->assign(value);
}
inline void Descriptors::set_descriptors(int index, const void* value, size_t size) {
  descriptors_.Mutable(index)->assign(
    reinterpret_cast<const char*>(value), size);
}
inline ::std::string* Descriptors::add_descriptors() {
  return descriptors_.Add();
}
inline void Descriptors::add_descriptors(const ::std::string& value) {
  descriptors_.Add()->assign(value);
}
inline void Descriptors::add_descriptors(const char* value) {
  descriptors_.Add()->assign(value);
}
inline void Descriptors::add_descriptors(const void* value, size_t size) {
  descriptors_.Add()->assign(reinterpret_cast<const char*>(value), size);
}
inline const ::google::protobuf::RepeatedPtrField< ::std::string>&
Descriptors::descriptors() const {
  return descriptors_;
}
inline ::google::protobuf::RepeatedPtrField< ::std::string>*
Descriptors::mutable_descriptors() {
  return &descriptors_;
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

#endif  // PROTOBUF_LookupQuery_2eproto__INCLUDED
