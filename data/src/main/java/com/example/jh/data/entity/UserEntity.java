package com.example.jh.data.entity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.squareup.sqldelight.RowMapper;

/**
 * Created by Administrator on 2017/5/3.
 */
@AutoValue
public abstract class UserEntity implements UserModel {

    @NonNull
    public static Builder builder() {
        return new AutoValue_UserEntity.Builder();
    }

    public static final UserModel.Factory<UserEntity> FACTORY = new UserModel.Factory<>(AutoValue_UserEntity::new);

    // 这一句加上就会错，是为什么？2017-11-3
//    public static final RowMapper<UserEntity> MAPPER = FACTORY.get_allMapper();


    public static TypeAdapter<UserEntity> typeAdapter(Gson gson) {
        return new AutoValue_UserEntity.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder _id(@Nullable Integer id);
        public abstract Builder username(@Nullable String username);
        public abstract Builder nickname(String nickname);
        public abstract Builder password(String password);
        public abstract Builder token(String token);
        public abstract Builder status(int status);
        public abstract Builder create_at(long create_at);



        public abstract UserEntity build();
    }
}
