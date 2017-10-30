package com.example.jh.data.entity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.squareup.sqldelight.RowMapper;

/**
 * Created by jinhui on 2017/10/30.
 * Email：1004260403@qq.com
 */

@AutoValue
public abstract class DeviceEn extends BaseEntity implements DeviceModel  {

    @NonNull
    public static Builder builder() {
        return new AutoValue_DeviceEn.Builder();
    }

    /**
     * 这个项目确实在那个地方应用，待分析——2017-10-30
     * @param gson
     * @return
     */
//    public static final DeviceModel.Factory<DeviceEn> FACTORY = new DeviceModel.Factory<>(AutoValue_DeviceEn::new);
//    public static final RowMapper<DeviceEn> MAPPER = FACTORY.get_allMapper();

    public static TypeAdapter<DeviceEn> typeAdapter(Gson gson) {
        return new AutoValue_DeviceEn.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder _id(@Nullable Integer id);
        public abstract Builder interval(Integer id);
        public abstract Builder imei(@Nullable String imei);
        public abstract Builder name(@Nullable String name);
        public abstract Builder phone(String phone);
        public abstract Builder ischeck(boolean ischeck);

        public abstract DeviceEn build();
    }
}
