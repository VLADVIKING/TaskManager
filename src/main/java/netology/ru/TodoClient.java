package netology.ru;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;


public class TodoClient {

    private String type;
    private String task;
    static TodoClient todoClient = new TodoClient();

    public static void main(String[] args) {

        try (Socket socket = new Socket("127.0.0.1", 8989);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            todoClient.type = "ADD";
            todoClient.task = "Завтрак";
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            writer.println(gson.toJson(todoClient));
            System.out.println(reader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
