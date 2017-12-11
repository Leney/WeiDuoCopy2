package com.zhongke.weiduo.library.retrofit;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by ${xingen} on 2017/11/7.
 */

public class SpecifiedJsonConverter extends Converter.Factory{

    private final Gson gson;

    private SpecifiedJsonConverter(Gson gson){
        this.gson=gson;
    }
    public static SpecifiedJsonConverter create(){
        return new SpecifiedJsonConverter(new Gson());
    }

    /**
     * response相应结果的解析
     * @param type
     * @param annotations
     * @param retrofit
     * @return
     */
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new JsonResponseBodyConverter<>(gson, adapter);
    }
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
        Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
            return new JsonRequestBodyConverter<>(gson, adapter);
    }
    /**
     *
     * @param <T>
     */
    private    final class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
        private final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
        private  final Charset UTF_8 = Charset.forName("UTF-8");

        private final Gson gson;
        private final TypeAdapter<T> adapter;

        JsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
            this.gson = gson;
            this.adapter = adapter;
        }

        @Override public RequestBody convert(T value) throws IOException {
            Buffer buffer = new Buffer();
            Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
            JsonWriter jsonWriter = gson.newJsonWriter(writer);
            adapter.write(jsonWriter, value);
            jsonWriter.close();
            return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
        }
    }
    /**
     * 定义一个转换器Converter
     * @param <T>
     */
    private final class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        private final Gson gson;
        private final TypeAdapter<T> adapter;
        JsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
            this.gson = gson;
            this.adapter = adapter;
        }
        @Override public T convert(ResponseBody value) throws IOException {
           // JsonReader jsonReader = gson.newJsonReader(value.charStream());
            String result=value.string();
            try {
                return adapter.fromJson(result.trim());
            } finally {
                value.close();
            }
        }
    }
}
