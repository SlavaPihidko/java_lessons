package qa.addressbook.Generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import qa.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Slava on 30.03.2016.
 */
public class GroupDataGenerator {

  @Parameter(names = "-c", description = "Group count")
  public int count;
  @Parameter(names = "-f", description = "Target file")
  public String file; // библиотека Jcommander напрямую работу с файлами не поддерживает
  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String []args) throws IOException {
    GroupDataGenerator generator = new GroupDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    }  catch (ParameterException ex){
       jCommander.usage();
      return;
    }
    generator.run();
    //int count = Integer.parseInt(args[0]);
    //File file = new File(args[1]);
  }

  private void run() throws IOException {
    List<GroupData> groups = generatorGroups(count);
    if(format.equals("csv")){
    saveAsCsv(groups,new File(file));}
    else if(format.equals("xml")){
      saveAsXml(groups,new File(file));
    } else{
      System.out.println("Unrecognized format"+format);
    }
  }

  private void saveAsXml(List<GroupData> groups, File file) throws IOException {
    XStream xstream = new XStream();
    //xstream.alias("group", GroupData.class);
    xstream.processAnnotations(GroupData.class);
    String xml = xstream.toXML(groups);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private  void saveAsCsv(List<GroupData> groups, File file) throws IOException {
    //System.out.println(new File(".").getAbsolutePath()); что бы узнать текущую директорию
    Writer writer = new FileWriter(file);
    for(GroupData group: groups){
      writer.write(String.format("%s;%s;%s\n",group.getName(),group.getHeader(),group.getFooter()));
    }
    writer.close();
  }

  private  List<GroupData> generatorGroups(int count) {
    List<GroupData> groups = new ArrayList<GroupData>();
    for (int i=0; i<count; i++) {
      groups.add(new GroupData().withName(String.format("test %s", i))
      .withHeader(String.format("header \n %s", i)).withFooter(String.format("footer \n %s", i))); // можно убрать \n что бы в одной строке было
    }
    return groups;
  }
}
