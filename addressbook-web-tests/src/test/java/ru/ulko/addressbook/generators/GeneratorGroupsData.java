package ru.ulko.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.ulko.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yulia on 07.09.2019.
 */
public class GeneratorGroupsData {

    @Parameter(names="-c", description = "Group count")
    public int count;

    @Parameter(names="-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        GeneratorGroupsData generator = new GeneratorGroupsData();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<GroupData> groups = generateGroups(count);
        saveFile(groups, new File(file));
    }

    private void saveFile(List<GroupData> groups, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (GroupData group : groups) {
            writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader()
                    , group.getFooter()));
        }
        writer.close();
    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName(String.format("name_%s", i))
                    .withHeader(String.format("header_%s", i))
                    .withFooter(String.format("footer_%s", i)));

        }
        return groups;
    }
}
