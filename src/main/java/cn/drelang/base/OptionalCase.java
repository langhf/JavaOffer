package cn.drelang.base;

import java.util.Optional;

/**
 * Created by Drelang on 2020/05/17 10:21
 */

interface IMessage {
    String getContent();
}

class MessageImpl implements IMessage {
    @Override
    public String getContent() {
        return "test message";
    }
}

class MessageUtil {
    public static Optional<IMessage> getMessage() {
        return Optional.ofNullable(null);
    }

    public static Optional<IMessage> getMessage1() {
        return Optional.of(new MessageImpl());
    }

    public static void useMessage(IMessage message) {
        if (message != null) {
            System.out.println(message.getContent());
        }
    }
}

public class OptionalCase {
    public static void main(String[] args) {
        IMessage message = MessageUtil.getMessage().orElse(new MessageImpl());
        IMessage message1 = MessageUtil.getMessage1().get();
        MessageUtil.useMessage(message);
        MessageUtil.useMessage(message1);
    }
}

