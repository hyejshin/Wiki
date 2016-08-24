package DTO;

public class BoardBean {
	// 게시판에서 내용을 저장하는 변수
	private int idx;
	private String title;
	private String writer;
	private String writeDate;
	private String modifier;
	private String modifyDate;
	private String category;
	private String image;
	private String explanation;
	private String valid;
	private String writerEmail;
	
	public BoardBean(){	}
	
	//getter, setter 함수. 값을 설정하고 불러온다.
	public int getIdx(){
		return idx;
	}
	public void setIdx(int idx){
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getValid(){
		return valid;
	}
	public void setValid(String valid){
		this.valid = valid;
	}
	public String getWriterEmail(){
		return writerEmail;
	}
	public void setWriterEmail(String writerEmail){
		this.writerEmail = writerEmail;
	}
}

