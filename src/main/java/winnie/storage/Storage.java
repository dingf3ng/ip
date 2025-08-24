package winnie.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;

import winnie.task.Deadline;
import winnie.task.Event;
import winnie.task.Task;
import winnie.task.Todo;
import winnie.util.DateTimeUtil;

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
                    System.err.println(
                            "Warning: Skipping corrupted line in data file: " 
                            + line);
                }
            }
        }
        scanner.close();
        return tasks;
    }

    private String taskToString(Task task) {
        String statusStr = task.isDone() ? "1" : "0";
        String typeStr = task.getTaskType()
                .toString().substring(0, 1);
        
        switch (task.getTaskType()) {
            case TODO:
                return typeStr 
                        + " | " + statusStr 
                        + " | " + task.getDescription();
            case DEADLINE:
                Deadline deadline = (Deadline) task;
                return typeStr 
                        + " | " + statusStr 
                        + " | " + task.getDescription() 
                        + " | " + DateTimeUtil.formatForStorage(deadline.getBy());
            case EVENT:
                Event event = (Event) task;
                return typeStr 
                        + " | " + statusStr 
                        + " | " + task.getDescription() 
                        + " | " + DateTimeUtil.formatForStorage(event.getFrom()) 
                        + " to " + DateTimeUtil.formatForStorage(event.getTo());
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
                LocalDateTime deadlineTime = DateTimeUtil.parseFromStorage(by);
                task = new Deadline(description, deadlineTime);
                break;
            case "E":
                if (parts.length < 4) {
                    throw new Exception("Missing event time");
                }
                String[] timeParts = parts[3].trim().split(" to ");
                if (timeParts.length != 2) {
                    throw new Exception("Invalid event time format");
                }
                String from = timeParts[0].trim();
                String to = timeParts[1].trim();
                LocalDateTime fromTime = DateTimeUtil.parseFromStorage(from);
                LocalDateTime toTime = DateTimeUtil.parseFromStorage(to);
                task = new Event(description, fromTime, toTime);
                break;
            default:
                throw new Exception("Unknown task type: " + type);
        }

        if (task != null && isDone) {
            task.markAsDone();
        }
        
        return task;
    }

}