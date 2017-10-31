package com.cbh.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import javax.persistence.Embedded;
import java.util.List;

@Generated("com.robohorse.robopojogenerator")
@Document(collection="hospitalProfile")
public class HospitalGeoResponse{

	@Id
	private String id;

	@JsonProperty("hosNo")
	private String hosNo;

	@JsonProperty("serviceItem")
	private String serviceItem;

	@JsonProperty("address")
	private String address;

	@JsonProperty("city")
	private String city;

	@JsonProperty("hasMap")
	private String hasMap;

	@JsonProperty("latitude")
	private String latitude;

	@JsonProperty("telephone")
	private String telephone;

	@JsonProperty("timeTable")
	private List<String> timeTable;

	@JsonProperty("legalName")
	private String legalName;

	@JsonProperty("hosCategory")
	private List<String> hosCategory;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("streetAddress")
	private String streetAddress;

	@JsonProperty("name")
	private String name;

	@JsonProperty("category")
	private String category;

	@JsonProperty("longitude")
	private String longitude;

	@JsonProperty("location")
    private Location location;

	private List<String> weekDay;

	private List<String> morning;

	private List<String> afternoo;

	private List<String> night;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setHosNo(String hosNo){
		this.hosNo = hosNo;
	}

	public String getHosNo(){
		return hosNo;
	}

	public void setServiceItem(String serviceItem){
		this.serviceItem = serviceItem;
	}

	public String getServiceItem(){
		return serviceItem;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setCity(String city){
		this.city = city;
	}


	public String getCity(){
		return city;
	}

	public void setHasMap(String hasMap){
		this.hasMap = hasMap;
	}

	public String getHasMap(){
		return hasMap;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setTelephone(String telephone){
		this.telephone = telephone;
	}

	public String getTelephone(){
		return telephone;
	}

	public void setTimeTable(List<String> timeTable){
		this.timeTable = timeTable;
	}

	public List<String> getTimeTable(){
		return timeTable;
	}

	public void setLegalName(String legalName){
		this.legalName = legalName;
	}

	public String getLegalName(){
		return legalName;
	}

	public void setHosCategory(List<String> hosCategory){
		this.hosCategory = hosCategory;
	}

	public List<String> getHosCategory(){
		return hosCategory;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setStreetAddress(String streetAddress){
		this.streetAddress = streetAddress;
	}

	public String getStreetAddress(){
		return streetAddress;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setLongitude(String longitude){
		this.longitude = longitude;
	}

	public String getLongitude(){
		return longitude;
	}

	public List<String> getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(List<String> weekDay) {
		this.weekDay = weekDay;
	}

	public List<String> getMorning() {
		return morning;
	}

	public void setMorning(List<String> morning) {
		this.morning = morning;
	}

	public List<String> getAfternoo() {
		return afternoo;
	}

	public void setAfternoo(List<String> afternoo) {
		this.afternoo = afternoo;
	}

	public List<String> getNight() {
		return night;
	}

	public void setNight(List<String> night) {
		this.night = night;
	}

	@Override
 	public String toString(){
		return 
			"HospitalGeoResponse{" + 
			"hosNo = '" + hosNo + '\'' + 
			",serviceItem = '" + serviceItem + '\'' + 
			",address = '" + address + '\'' + 
			",city = '" + city + '\'' + 
			",hasMap = '" + hasMap + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",telephone = '" + telephone + '\'' + 
			",timeTable = '" + timeTable + '\'' + 
			",legalName = '" + legalName + '\'' + 
			",hosCategory = '" + hosCategory + '\'' + 
			",phone = '" + phone + '\'' + 
			",streetAddress = '" + streetAddress + '\'' + 
			",name = '" + name + '\'' + 
			",category = '" + category + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		HospitalGeoResponse that = (HospitalGeoResponse) o;

		if (hosNo != null ? !hosNo.equals(that.hosNo) : that.hosNo != null) return false;
		if (serviceItem != null ? !serviceItem.equals(that.serviceItem) : that.serviceItem != null) return false;
		if (address != null ? !address.equals(that.address) : that.address != null) return false;
		if (city != null ? !city.equals(that.city) : that.city != null) return false;
		if (hasMap != null ? !hasMap.equals(that.hasMap) : that.hasMap != null) return false;
		if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
		if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
		if (timeTable != null ? !timeTable.equals(that.timeTable) : that.timeTable != null) return false;
		if (legalName != null ? !legalName.equals(that.legalName) : that.legalName != null) return false;
		if (hosCategory != null ? !hosCategory.equals(that.hosCategory) : that.hosCategory != null) return false;
		if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
		if (streetAddress != null ? !streetAddress.equals(that.streetAddress) : that.streetAddress != null)
			return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (category != null ? !category.equals(that.category) : that.category != null) return false;
		if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = hosNo != null ? hosNo.hashCode() : 0;
		result = 31 * result + (serviceItem != null ? serviceItem.hashCode() : 0);
		result = 31 * result + (address != null ? address.hashCode() : 0);
		result = 31 * result + (city != null ? city.hashCode() : 0);
		result = 31 * result + (hasMap != null ? hasMap.hashCode() : 0);
		result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
		result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
		result = 31 * result + (timeTable != null ? timeTable.hashCode() : 0);
		result = 31 * result + (legalName != null ? legalName.hashCode() : 0);
		result = 31 * result + (hosCategory != null ? hosCategory.hashCode() : 0);
		result = 31 * result + (phone != null ? phone.hashCode() : 0);
		result = 31 * result + (streetAddress != null ? streetAddress.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (category != null ? category.hashCode() : 0);
		result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
		return result;
	}

}