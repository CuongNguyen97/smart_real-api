package com.smartreal.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Like {
    private boolean isLiked;

    public Like(boolean isLiked) {
        this.isLiked = isLiked;
    }

    @JsonProperty("isLiked")
    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public static Like like() {
        return new Like(true);
    }

    public static Like unLike() {
        return new Like(false);
    }
}
