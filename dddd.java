import com.mirai.BookResourceRetrofit2;
import javafx.application.Application;
import javafx.stage.Stage;
import okhttp3.*;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import java.io.File;
import java.io.IOException;

public class dddd extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file", "test.txt", RequestBody.create(MediaType.parse("text/plain"), new File("C:\\Users\\Win10\\Desktop\\45191623_347357725827668_4427863329419034624_n.jpg")))
                        .build();

                // Request customization: add request headers
                Request request = original.newBuilder()
                        .method("POST", requestBody)
                        .url(original.url().url().toString() + "\\")
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        BookResourceRetrofit2 bookResource = retrofit.create(BookResourceRetrofit2.class);


        Call<Object> call = bookResource.addBook();
        retrofit2.Response<Object> response = call.execute();
        String x ="";
    }
}
