package main.cn.edu.buaa.cloudplatform.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandParser {
    private Map<String, Command> commands = new HashMap<>();

    /**
     * 注册命令
     * @param commandName 命令名称
     * @param command 命令类实例
     */
    public void registerCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    /**
     * 解析并执行命令
     * @param input 用户输入的命令字符串
     */
    public void parseAndExecute(String input) {
        // 去除首尾空白字符
        input = input.trim();

        // 检查输入是否为空
        if (input.isEmpty()) {
            return; // 如果输入为空，直接返回，不执行任何命令
        }

        // 按空白字符分割输入字符串
        String[] parts = input.split("\\s+");
        if (parts.length == 0) {
            System.out.println("No command entered");
            return;
        }

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
