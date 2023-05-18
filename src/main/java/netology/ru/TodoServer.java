package netology.ru;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {

    private int port;
    private Todos todos;
    static TodoServer todoServer = new TodoServer();
    String type;
    String task;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public TodoServer() {
    }

    public void start() {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = server.accept();
                     PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ) {
                    String jsonText = reader.readLine();
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    todoServer = gson.fromJson(jsonText, TodoServer.class);
                    if (todoServer.type.equals("ADD")) {
                        todos.addTask(todoServer.task);
                        writer.println(todos.getAllTasks());
                    } else if (todoServer.type.equals("REMOVE")) {
                        todos.removeTask(todoServer.task);
                        writer.println(todos.getAllTasks());
                    } else if (todoServer.type.equals("RESTORE")) {
                        todos.restoreTask();
                        writer.println(todos.getAllTasks());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
