package cc.shawn.zzj.bean;

import java.util.List;

/**
 * Created by shawn on 17/2/19.
 */

/*
{
		"id":"58a5522e599014decb00987f",
		"owner":"FFF1-33",
		"images":null,
		"message":"我喜欢榴莲酥3",
		"comments":[{
			"ownerUUID":"FF1","friendUUID":"EE1","message":"不错不错1","commentID":"b28a6d79-5a6c-4273-bdcb-d12e9678daa9"},
			{"ownerUUID":"FF2","friendUUID":"EE3","message":"不错不错2","commentID":"f11300dc-d9e8-460d-82c9-58b2ede6a2e7"}
			],
		"createDate":1487229486991}
 */
public class SocialItem {
    public String id;
    public String owner;
    public List<String> images;
    public String message;
    public List<ComItem> comments;
    public String createDate;
    public boolean isExpand;

    public boolean isExpand(){
        return isExpand;
    }

    public void setExpand(boolean expand) {
        this.isExpand = expand;
    }

    @Override
    public String toString() {
        return "SocialItem{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", images=" + images +
                ", message='" + message + '\'' +
                ", comments=" + comments +
                ", createDate='" + createDate + '\'' +
                ", isExpand=" + isExpand +
                '}';
    }

    public boolean hasComments(){
        if (comments != null && comments.size()>0)
            return true;
        else return false;
    }

}
