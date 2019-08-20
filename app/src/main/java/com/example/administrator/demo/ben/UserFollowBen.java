package com.example.administrator.demo.ben;

import java.io.Serializable;

/**关注/粉丝**/
public class UserFollowBen implements Serializable {
    String pic;
    String name;
    String qianming;
    String tag;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQianming() {
        return qianming;
    }

    public void setQianming(String qianming) {
        this.qianming = qianming;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
