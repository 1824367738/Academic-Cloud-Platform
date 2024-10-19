package main.cn.edu.buaa.cloudplatform.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandParser {
    private Map<String, Command> commands = new HashMap<>();

    public void registerCommand(String commandName, Command command){
        commands.put(commandName, command);
    }

    public void parseAndExecute(String input){
        //去除首尾空白字符，按照空白字符分割字符串
        String[] parts = input.trim().split("\\s+");

        // 提取命令名称
        String commandName = parts[0];
        // 提取参数
        String[] args = new String[parts.length - 1];
        System.arraycopy(parts, 1, args, 0, args.length);

        // 查找并执行命令
        Command command = commands.get(commandName);
        if (command != null) {
            command.execute(args);
        } else {
            System.out.println("Command '" + commandName + "' not found");
        }
    }
}
