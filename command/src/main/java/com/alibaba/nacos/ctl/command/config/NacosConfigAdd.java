package com.alibaba.nacos.ctl.command.config;

import com.alibaba.nacos.ctl.core.LogicHandler;
import com.alibaba.nacos.ctl.core.exception.HandlerException;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.alibaba.nacos.ctl.command.utils.HintUtils.DESCRIPTION_HEADING;
import static com.alibaba.nacos.ctl.command.utils.HintUtils.HEADER_HEADING;
import static com.alibaba.nacos.ctl.command.utils.HintUtils.NAME_ADD;
import static com.alibaba.nacos.ctl.command.utils.HintUtils.OPTION_LIST_HEADING;
import static com.alibaba.nacos.ctl.command.utils.HintUtils.PARAMETER_LIST_HEADING;
import static com.alibaba.nacos.ctl.command.utils.HintUtils.SORT_OPTIONS;
import static com.alibaba.nacos.ctl.command.utils.HintUtils.SYNOPSIS_HEADING;

/**
 * add a config
 *
 * @author lehr
 */
@CommandLine.Command(name = NAME_ADD, sortOptions = SORT_OPTIONS, headerHeading = HEADER_HEADING, synopsisHeading = SYNOPSIS_HEADING, descriptionHeading = DESCRIPTION_HEADING, parameterListHeading = PARAMETER_LIST_HEADING, optionListHeading = OPTION_LIST_HEADING, header = "publish configurations", description = "It publishes configurations in Nacos.")
public class NacosConfigAdd implements Runnable {
    
    @CommandLine.Parameters(paramLabel = "<group>", description = "Configuration group.")
    String group;
    
    @CommandLine.Parameters(paramLabel = "<dataId>", description = "Configuration ID.")
    String dataId;
    
    @CommandLine.Parameters(paramLabel = "<content or file path>", description = "Configuration content or file path.")
    String content;
    
    @CommandLine.Option(names = {"-f", "--file"}, description = "If it is a config content from file?")
    boolean isFile = false;
    
    @CommandLine.Option(names = {"-t", "--type"}, paramLabel = "<type>", description = "Configuration type.")
    String type;
    
    
    @Override
    public void run() {
        
        if (isFile) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get(new File(content).getPath()));
                content = new String(bytes);
            } catch (IOException e) {
                System.out.println("failed to read file");
                return;
            }
        }
        
        try {
            if (LogicHandler.postConfig(group, dataId, content, type)) {
                System.out.println("done");
                return;
            }
            System.out.printf("publish config group:%s, dataid:%s failed%n", group, dataId);
        } catch (HandlerException e) {
            System.out.println(e.getMessage());
        }
        
    }
}
