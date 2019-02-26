package com.monkey.kafka.examle.consumer;

import org.apache.commons.cli.*;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;

import java.util.Properties;

/**
 * KafkaAdmin
 *
 * @author yong.han
 * 2019/1/18
 */
public class KafkaAdmin {
    public static void main(String[] args) throws ParseException {
        Properties props = commandToProperties(args);
        if (null == props) {
            System.exit(0);
        }

        AdminClient admin = AdminClient.create(props);





        admin.close();

    }


    private static Properties commandToProperties(String[] args) throws ParseException {
        Properties props = new Properties();

        Options ops = buildCommandOptions();
        CommandLine commandLine = new DefaultParser().parse(ops, args);

        if (commandLine.hasOption("h")) {
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp("kafka admin", ops, true);
        }

        return props;
    }


    private static Options buildCommandOptions() {
        Options ops = new Options();

        ops.addOption(new Option("h", "print help"));

        ops.addOption(new Option("c", true, "config file"));
        ops.addOption(new Option("s", true, "broker server address list, eg: 192.168.6.65:9092,192.168.6.66:9092,192.168.6.67:9093"));

        return ops;
    }





}
