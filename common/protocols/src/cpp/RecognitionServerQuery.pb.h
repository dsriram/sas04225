// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: RecognitionServerQuery.proto

#ifndef PROTOBUF_RecognitionServerQuery_2eproto__INCLUDED
#define PROTOBUF_RecognitionServerQuery_2eproto__INCLUDED

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
void  protobuf_AddDesc_RecognitionServerQuery_2eproto();
void protobuf_AssignDesc_RecognitionServerQuery_2eproto();
void protobuf_ShutdownFile_RecognitionServerQuery_2eproto();

class Query;

enum QueryType {
  LOCATION_BASED_QUERY = 0,
  GENERAL_QUERY = 1
};
bool QueryType_IsValid(int value);
const QueryType QueryType_MIN = LOCATION_BASED_QUERY;
const QueryType QueryType_MAX = GENERAL_QUERY;
const int QueryType_ARRAYSIZE = QueryType_MAX + 1;

const ::google::protobuf::EnumDescriptor* QueryType_descriptor();
inline const ::std::string& QueryType_Name(QueryType value) {
  return ::google::protobuf::internal::NameOfEnum(
    QueryType_descriptor(), value);
}
inline bool QueryType_Parse(
    const ::std::string& name, QueryType* value) {
  return ::google::protobuf::internal::ParseNamedEnum<QueryType>(
    QueryType_descriptor(), name, value);
}
enum QueryDataType {
  JPEG_IMAGE = 0,
  FREAK_DESCRIPTOR = 1
};
bool QueryDataType_IsValid(int value);
const QueryDataType QueryDataType_MIN = JPEG_IMAGE;
const QueryDataType QueryDataType_MAX = FREAK_DESCRIPTOR;
const int QueryDataType_ARRAYSIZE = QueryDataType_MAX + 1;

const ::google::protobuf::EnumDescriptor* QueryDataType_descriptor();
inline const ::std::string& QueryDataType_Name(QueryDataType value) {
  return ::google::protobuf::internal::NameOfEnum(
    QueryDataType_descriptor(), value);
}
inline bool QueryDataType_Parse(
    const ::std::string& name, QueryDataType* value) {
  return ::google::protobuf::internal::ParseNamedEnum<QueryDataType>(
    QueryDataType_descriptor(), name, value);
}
// ===================================================================

class Query : public ::google::protobuf::Message {
 public:
  Query();
  virtual ~Query();
  
  Query(const Query& from);
  
  inline Query& operator=(const Query& from) {
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
  static const Query& default_instance();
  
  void Swap(Query* other);
  
  // implements Message ----------------------------------------------
  
  Query* New() const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const Query& from);
  void MergeFrom(const Query& from);
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
  
  // required .org.sas04225.proto.QueryType queryType = 2;
  inline bool has_querytype() const;
  inline void clear_querytype();
  static const int kQueryTypeFieldNumber = 2;
  inline org::sas04225::proto::QueryType querytype() const;
  inline void set_querytype(org::sas04225::proto::QueryType value);
  
  // required .org.sas04225.proto.QueryDataType queryDataType = 3;
  inline bool has_querydatatype() const;
  inline void clear_querydatatype();
  static const int kQueryDataTypeFieldNumber = 3;
  inline org::sas04225::proto::QueryDataType querydatatype() const;
  inline void set_querydatatype(org::sas04225::proto::QueryDataType value);
  
  // required uint32 location_id = 4;
  inline bool has_location_id() const;
  inline void clear_location_id();
  static const int kLocationIdFieldNumber = 4;
  inline ::google::protobuf::uint32 location_id() const;
  inline void set_location_id(::google::protobuf::uint32 value);
  
  // required bytes queryData = 5;
  inline bool has_querydata() const;
  inline void clear_querydata();
  static const int kQueryDataFieldNumber = 5;
  inline const ::std::string& querydata() const;
  inline void set_querydata(const ::std::string& value);
  inline void set_querydata(const char* value);
  inline void set_querydata(const void* value, size_t size);
  inline ::std::string* mutable_querydata();
  inline ::std::string* release_querydata();
  
  // @@protoc_insertion_point(class_scope:org.sas04225.proto.Query)
 private:
  inline void set_has_request_id();
  inline void clear_has_request_id();
  inline void set_has_querytype();
  inline void clear_has_querytype();
  inline void set_has_querydatatype();
  inline void clear_has_querydatatype();
  inline void set_has_location_id();
  inline void clear_has_location_id();
  inline void set_has_querydata();
  inline void clear_has_querydata();
  
  ::google::protobuf::UnknownFieldSet _unknown_fields_;
  
  ::google::protobuf::uint64 request_id_;
  int querytype_;
  int querydatatype_;
  ::std::string* querydata_;
  ::google::protobuf::uint32 location_id_;
  
  mutable int _cached_size_;
  ::google::protobuf::uint32 _has_bits_[(5 + 31) / 32];
  
  friend void  protobuf_AddDesc_RecognitionServerQuery_2eproto();
  friend void protobuf_AssignDesc_RecognitionServerQuery_2eproto();
  friend void protobuf_ShutdownFile_RecognitionServerQuery_2eproto();
  
  void InitAsDefaultInstance();
  static Query* default_instance_;
};
// ===================================================================


// ===================================================================

// Query

// required uint64 request_id = 1;
inline bool Query::has_request_id() const {
  return (_has_bits_[0] & 0x00000001u) != 0;
}
inline void Query::set_has_request_id() {
  _has_bits_[0] |= 0x00000001u;
}
inline void Query::clear_has_request_id() {
  _has_bits_[0] &= ~0x00000001u;
}
inline void Query::clear_request_id() {
  request_id_ = GOOGLE_ULONGLONG(0);
  clear_has_request_id();
}
inline ::google::protobuf::uint64 Query::request_id() const {
  return request_id_;
}
inline void Query::set_request_id(::google::protobuf::uint64 value) {
  set_has_request_id();
  request_id_ = value;
}

// required .org.sas04225.proto.QueryType queryType = 2;
inline bool Query::has_querytype() const {
  return (_has_bits_[0] & 0x00000002u) != 0;
}
inline void Query::set_has_querytype() {
  _has_bits_[0] |= 0x00000002u;
}
inline void Query::clear_has_querytype() {
  _has_bits_[0] &= ~0x00000002u;
}
inline void Query::clear_querytype() {
  querytype_ = 0;
  clear_has_querytype();
}
inline org::sas04225::proto::QueryType Query::querytype() const {
  return static_cast< org::sas04225::proto::QueryType >(querytype_);
}
inline void Query::set_querytype(org::sas04225::proto::QueryType value) {
  GOOGLE_DCHECK(org::sas04225::proto::QueryType_IsValid(value));
  set_has_querytype();
  querytype_ = value;
}

// required .org.sas04225.proto.QueryDataType queryDataType = 3;
inline bool Query::has_querydatatype() const {
  return (_has_bits_[0] & 0x00000004u) != 0;
}
inline void Query::set_has_querydatatype() {
  _has_bits_[0] |= 0x00000004u;
}
inline void Query::clear_has_querydatatype() {
  _has_bits_[0] &= ~0x00000004u;
}
inline void Query::clear_querydatatype() {
  querydatatype_ = 0;
  clear_has_querydatatype();
}
inline org::sas04225::proto::QueryDataType Query::querydatatype() const {
  return static_cast< org::sas04225::proto::QueryDataType >(querydatatype_);
}
inline void Query::set_querydatatype(org::sas04225::proto::QueryDataType value) {
  GOOGLE_DCHECK(org::sas04225::proto::QueryDataType_IsValid(value));
  set_has_querydatatype();
  querydatatype_ = value;
}

// required uint32 location_id = 4;
inline bool Query::has_location_id() const {
  return (_has_bits_[0] & 0x00000008u) != 0;
}
inline void Query::set_has_location_id() {
  _has_bits_[0] |= 0x00000008u;
}
inline void Query::clear_has_location_id() {
  _has_bits_[0] &= ~0x00000008u;
}
inline void Query::clear_location_id() {
  location_id_ = 0u;
  clear_has_location_id();
}
inline ::google::protobuf::uint32 Query::location_id() const {
  return location_id_;
}
inline void Query::set_location_id(::google::protobuf::uint32 value) {
  set_has_location_id();
  location_id_ = value;
}

// required bytes queryData = 5;
inline bool Query::has_querydata() const {
  return (_has_bits_[0] & 0x00000010u) != 0;
}
inline void Query::set_has_querydata() {
  _has_bits_[0] |= 0x00000010u;
}
inline void Query::clear_has_querydata() {
  _has_bits_[0] &= ~0x00000010u;
}
inline void Query::clear_querydata() {
  if (querydata_ != &::google::protobuf::internal::kEmptyString) {
    querydata_->clear();
  }
  clear_has_querydata();
}
inline const ::std::string& Query::querydata() const {
  return *querydata_;
}
inline void Query::set_querydata(const ::std::string& value) {
  set_has_querydata();
  if (querydata_ == &::google::protobuf::internal::kEmptyString) {
    querydata_ = new ::std::string;
  }
  querydata_->assign(value);
}
inline void Query::set_querydata(const char* value) {
  set_has_querydata();
  if (querydata_ == &::google::protobuf::internal::kEmptyString) {
    querydata_ = new ::std::string;
  }
  querydata_->assign(value);
}
inline void Query::set_querydata(const void* value, size_t size) {
  set_has_querydata();
  if (querydata_ == &::google::protobuf::internal::kEmptyString) {
    querydata_ = new ::std::string;
  }
  querydata_->assign(reinterpret_cast<const char*>(value), size);
}
inline ::std::string* Query::mutable_querydata() {
  set_has_querydata();
  if (querydata_ == &::google::protobuf::internal::kEmptyString) {
    querydata_ = new ::std::string;
  }
  return querydata_;
}
inline ::std::string* Query::release_querydata() {
  clear_has_querydata();
  if (querydata_ == &::google::protobuf::internal::kEmptyString) {
    return NULL;
  } else {
    ::std::string* temp = querydata_;
    querydata_ = const_cast< ::std::string*>(&::google::protobuf::internal::kEmptyString);
    return temp;
  }
}


// @@protoc_insertion_point(namespace_scope)

}  // namespace proto
}  // namespace sas04225
}  // namespace org

#ifndef SWIG
namespace google {
namespace protobuf {

template <>
inline const EnumDescriptor* GetEnumDescriptor< org::sas04225::proto::QueryType>() {
  return org::sas04225::proto::QueryType_descriptor();
}
template <>
inline const EnumDescriptor* GetEnumDescriptor< org::sas04225::proto::QueryDataType>() {
  return org::sas04225::proto::QueryDataType_descriptor();
}

}  // namespace google
}  // namespace protobuf
#endif  // SWIG

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_RecognitionServerQuery_2eproto__INCLUDED