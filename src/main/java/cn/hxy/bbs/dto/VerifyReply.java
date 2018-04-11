package cn.hxy.bbs.dto;

public class VerifyReply extends VerifyPost{
private int replyStatus;
private String reply;
private String text;

public int getReplyStatus() {
	return replyStatus;
}
public void setReplyStatus(int replyStatus) {
	this.replyStatus = replyStatus;
}
public String getReply() {
	return reply;
}
public void setReply(String reply) {
	String str = reply.replaceAll("<(S*?)[^>]*>.*?|<.*? />", "");
	setText(str);
	this.reply = reply;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}

}
