package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(Task[] tasks) throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        FileWriter writer = new FileWriter(file);
        for (Task task : tasks) {
            String taskData = taskToString(task);
            writer.write(taskData + System.lineSeparator());
        }
        writer.close();
    }

    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        
        if (!file.exists()) {
            return tasks;
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                try {
                    Task task = stringToTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                } catch (Exception e) {
                    System.err.println("Warning: Skipping corrupted line in data file: " + line);
                }
            }
        }
        scanner.close();
        return tasks;
    }

    private String taskToString(Task task) {
        String statusStr = task.isDone() ? "1" : "0";
        String typeStr = task.getTaskType().toString().substring(0, 1);
        
        switch (task.getTaskType()) {
            case TODO:
                return typeStr + " | " + statusStr + " | " + task.getDescription();
            case DEADLINE:
                Deadline deadline = (Deadline) task;
                return typeStr + " | " + statusStr + " | " + task.getDescription() + " | " + getDeadlineBy(deadline);
            case EVENT:
                Event event = (Event) task;
                return typeStr + " | " + statusStr + " | " + task.getDescription() + " | " + getEventTiming(event);
            default:
                return "";
        }
    }

    private Task stringToTask(String data) throws Exception {
        String[] parts = data.split(" \\| ");
        if (parts.length < 3) {
            throw new Exception("Invalid data format");
        }

        String type = parts[0].trim();
        String status = parts[1].trim();
        String description = parts[2].trim();
        boolean isDone = "1".equals(status);

        Task task = null;
        
        switch (type.toUpperCase()) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length < 4) {
                    throw new Exception("Missing deadline time");
                }
                String by = parts[3].trim();
                task = new Deadline(description, by);
                break;
            case "E":
                if (parts.length < 4) {
                    throw new Exception("Missing event time");
                }
                String[] timeParts = parts[3].trim().split(" to ");
                if (timeParts.length != 2) {
                    timeParts = parts[3].trim().split("-");
                    if (timeParts.length != 2) {
                        throw new Exception("Invalid event time format");
                    }
                }
                String from = timeParts[0].trim();
                String to = timeParts[1].trim();
                task = new Event(description, from, to);
                break;
            default:
                throw new Exception("Unknown task type: " + type);
        }

        if (task != null && isDone) {
            task.markAsDone();
        }
        
        return task;
    }

    private String getDeadlineBy(Deadline deadline) {
        String str = deadline.toString();
        int start = str.indexOf("(by: ") + 5;
        int end = str.lastIndexOf(")");
        return str.substring(start, end);
    }

    private String getEventTiming(Event event) {
        String str = event.toString();
        int fromStart = str.indexOf("(from: ") + 7;
        int fromEnd = str.indexOf(" to: ");
        int toEnd = str.lastIndexOf(")");
        String from = str.substring(fromStart, fromEnd);
        String to = str.substring(fromEnd + 5, toEnd);
        return from + " to " + to;
    }
}