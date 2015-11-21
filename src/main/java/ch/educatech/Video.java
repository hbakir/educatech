package ch.educatech;

import org.springframework.data.annotation.Id;

public class Video {
	@Id
    private String id;
    private String streams;
    private String category;
    private String title;
    private String description;
    private String duration;
    private String fullLength;
    private String publishedDate;
    private String img;
    private String url;
    private String thumbnail;
    private String numberOfPlays;
    private String isCatchUp;
    private String homesection;
    private String preview_image_url;
    private String durationS;
    private String streams_hds;
    private String streams_hds_sd;
    private String streams_hls;
    private String streams_hls_sd;
    private String program;
    private boolean pedagogic;
    
    
    

//    private String json;
//    private String intro;
//    private String contentType;
//    
//    private String articleType;
//    private String creation;
//    private String publication;
//    private String expiration;
//    private String modification;
//    
//    private String channel;
//    private String video;
//    private String cutin;
//    private String cutout;
//    private String cut;
//    private String plays;
//    private String geoloc;
//    private String lmgId;
//    private String isPlayable;
//    private String genre;

    public Video() {}




	public Video(String id, String img, String numberOfPlays, String isCatchUp, String homesection,
		String preview_image_url, String durationS, String streams, String category, String title, String description,
		String duration, String fullLength, String publishedDate, String thumbnail) {
	super();
	this.id = id;
	this.img = img;
	this.numberOfPlays = numberOfPlays;
	this.isCatchUp = isCatchUp;
	this.homesection = homesection;
	this.preview_image_url = preview_image_url;
	this.durationS = durationS;
	this.streams = streams;
	this.category = category;
	this.title = title;
	this.description = description;
	this.duration = duration;
	this.fullLength = fullLength;
	this.publishedDate = publishedDate;
	this.thumbnail = thumbnail;
}




	public String getId() {
		return id;
	}




	public String getImg() {
		return img;
	}




	public String getNumberOfPlays() {
		return numberOfPlays;
	}




	public String getIsCatchUp() {
		return isCatchUp;
	}




	public String getHomesection() {
		return homesection;
	}




	public String getPreview_image_url() {
		return preview_image_url;
	}




	public String getDurationS() {
		return durationS;
	}




	public String getStreams() {
		return streams;
	}




	public String getCategory() {
		return category;
	}




	public String getTitle() {
		return title;
	}




	public String getDescription() {
		return description;
	}




	public String getDuration() {
		return duration;
	}




	public String getFullLength() {
		return fullLength;
	}




	public String getPublishedDate() {
		return publishedDate;
	}




	public String getThumbnail() {
		return thumbnail;
	}




	public void setId(String id) {
		this.id = id;
	}




	public void setImg(String img) {
		this.img = img;
	}




	public void setNumberOfPlays(String numberOfPlays) {
		this.numberOfPlays = numberOfPlays;
	}




	public void setIsCatchUp(String isCatchUp) {
		this.isCatchUp = isCatchUp;
	}




	public void setHomesection(String homesection) {
		this.homesection = homesection;
	}




	public void setPreview_image_url(String preview_image_url) {
		this.preview_image_url = preview_image_url;
	}




	public void setDurationS(String durationS) {
		this.durationS = durationS;
	}




	public void setStreams(String streams) {
		this.streams = streams;
	}




	public void setCategory(String category) {
		this.category = category;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public void setDuration(String duration) {
		this.duration = duration;
	}




	public void setFullLength(String fullLength) {
		this.fullLength = fullLength;
	}




	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}




	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}




	public String getProgram() {
		return program;
	}




	public String getUrl() {
		return url;
	}




	public void setProgram(String program) {
		this.program = program;
	}




	public void setUrl(String url) {
		this.url = url;
	}




	public boolean isPedagogic() {
		return pedagogic;
	}




	public void setPedagogic(boolean pedagogic) {
		this.pedagogic = pedagogic;
	}




	public String getStreams_hds() {
		return streams_hds;
	}




	public String getStreams_hds_sd() {
		return streams_hds_sd;
	}




	public String getStreams_hls() {
		return streams_hls;
	}




	public String getStreams_hls_sd() {
		return streams_hls_sd;
	}




	public void setStreams_hds(String streams_hds) {
		this.streams_hds = streams_hds;
	}




	public void setStreams_hds_sd(String streams_hds_sd) {
		this.streams_hds_sd = streams_hds_sd;
	}




	public void setStreams_hls(String streams_hls) {
		this.streams_hls = streams_hls;
	}




	public void setStreams_hls_sd(String streams_hls_sd) {
		this.streams_hls_sd = streams_hls_sd;
	}
	

    
}