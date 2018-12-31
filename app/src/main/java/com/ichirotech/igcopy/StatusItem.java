package com.ichirotech.igcopy;

class StatusItem {
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    String img ;

    StatusItem (int i){
        this.img = String.valueOf(i);
    }
}
