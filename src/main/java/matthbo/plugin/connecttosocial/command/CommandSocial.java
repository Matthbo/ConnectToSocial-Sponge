package matthbo.plugin.connecttosocial.command;

import com.google.common.base.Optional;
import matthbo.plugin.connecttosocial.ConnectToSocial;
import matthbo.plugin.connecttosocial.Refs;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandCallable;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;

import java.io.*;
import java.util.Collections;
import java.util.List;

public class CommandSocial implements CommandCallable {

    private ConnectToSocial CTS = ConnectToSocial.instance;

    public void resetFile(String playerName){
        File dataFolder = CTS.getDataFolder();
        if(!dataFolder.exists()) dataFolder.mkdir();
        File saveTo = new File(dataFolder, playerName + ".dat");
        if(saveTo.exists()) saveTo.delete();
        if(!saveTo.exists()) return;
    }
    public void fileToMessage(CommandSource sender, String target){
        try{
            File dataFolder = CTS.getDataFolder();
            File list = new File(dataFolder, "/" + target + ".dat");
            if(!list.exists()) {sender.sendMessage(noList());}
            if(list.exists()){
                BufferedReader br = new BufferedReader(new FileReader(dataFolder + "/" + target + ".dat"));
                String str;
                sender.sendMessage(playersMedia(target));
                while((str = br.readLine()) != null){
                    sender.sendMessage(setTo("", str));
                }
                br.close();
            }
        }catch(Exception e){e.printStackTrace();}
    }

    private Text playersMedia(String target){
        return Texts.builder(Refs.pluginMSG).color(TextColors.DARK_GREEN).append(Texts.builder(target).color(TextColors.RESET).append(Texts.builder("'s Social Media:").build()).build()).build();
    }

    private Text noList(){
        return Texts.builder(Refs.pluginMSG).color(TextColors.DARK_GREEN).append(Texts.of("Nothing!")).build();
    }

    public void MediaCMD(String url, String playerName, String medium){
        try{
            File dataFolder = CTS.getDataFolder();
            if(!dataFolder.exists()) dataFolder.mkdir();
            File saveTo = new File(dataFolder, playerName + ".dat");
            if(!saveTo.exists()) saveTo.createNewFile();
            FileWriter fw = new FileWriter(saveTo, true);
            PrintWriter pw = new PrintWriter(fw);

            if (medium.equals("youtube")) {
                pw.println("Youtube: " + url);
            } else if (medium.equals("twitch")) {
                pw.println("Twitch: " + url);
            } else if (medium.equals("twitter")) {
                pw.println("Twitter: " + url);
            } else if (medium.equals("facebook")) {
                pw.println("Facebook: " + url);
            } else if (medium.equals("skype")) {
                pw.println("Skype: " + url);
            } else if (medium.equals("instagram")) {
                pw.println("Instagram: " + url);
            } else if (medium.equals("steam")) {
                pw.println("Steam: " + url);
            } else if (medium.equals("email")) {
                pw.println("E-Mail: " + url);
            }
            pw.flush();
            pw.close();
        }catch(IOException e){e.printStackTrace();}
    }

    private Text wrngSender(){
        return Texts.builder(Refs.pluginMSG).color(TextColors.DARK_GREEN).append(Texts.builder("Player Command Only!").color(TextColors.RED).build()).build();
    }

    private Text media1(){
        return Texts.builder(Refs.pluginMSG).color(TextColors.DARK_GREEN).append(Texts.builder("---[Media]---").color(TextColors.RESET).build()).build();
    }

    private Text media2(){
        return Texts.builder(Refs.pluginMSG).color(TextColors.DARK_GREEN).append(Texts.builder("youtube, twitch, twitter, facebook, skype, instagram, steam, email").color(TextColors.BLUE).build()).build();
    }

    private Text usage(String usage){
        return Texts.builder(Refs.pluginMSG).color(TextColors.DARK_GREEN).append(Texts.builder(usage).color(TextColors.RED).build()).build();
    }

    private Text example(String example){
        return Texts.builder(Refs.pluginMSG).color(TextColors.DARK_GREEN).append(Texts.builder("Example: ").color(TextColors.RED).append(Texts.builder(example).color(TextColors.BLUE).build()).build()).build();
    }

    private Text setTo(String msg1, String msg2){
        return Texts.builder(Refs.pluginMSG).color(TextColors.DARK_GREEN).append(Texts.builder(msg1).color(TextColors.RESET).append(Texts.builder(msg2).color(TextColors.BLUE).build()).build()).build();
    }

    private  Text supported(String msg1, String msg2){
        return Texts.builder(Refs.pluginMSG).color(TextColors.DARK_GREEN).append(Texts.builder(msg1).color(TextColors.RED).append(Texts.builder(msg2).color(TextColors.BLUE).build()).build()).build();
    }

    @Override
    public Optional process(CommandSource sender, String arguments) throws CommandException {
        if(sender instanceof Player){
            Player player = (Player)sender;
            String[] args = arguments.split(" ");
            if(args.length == 1 && !args[0].equalsIgnoreCase("reset") && !(args[0].length() < 2)){
                Player target = CTS.getGame().getServer().getPlayer(args[0]).orNull();
                if(target != null){
                    fileToMessage(player, target.getName());
                    return Optional.of(CommandResult.success());
                }else{
                    player.sendMessage(usage("Player is not online or does not exist!"));
                    return Optional.of(CommandResult.success());
                }
            }
            else if(args.length == 1 && args[0].equalsIgnoreCase("reset")){

                resetFile(player.getName());
                player.sendMessage(usage("Your Social Media List has been reset"));

                return Optional.of(CommandResult.success());
            }

				/*Youtube*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("youtube")){

                if(args[2].startsWith("http://") || args[2].startsWith("https://")){

                    MediaCMD(args[2], player.getName(), "youtube");
                    player.sendMessage(setTo("Youtube URL is set to: ", args[2]));

                    return Optional.of(CommandResult.success());
                }else{
                    player.sendMessage(usage("URL Is Not Valid!"));
                    player.sendMessage(example("http://google.com"));
                    player.sendMessage(example("https://google.com"));
                    return Optional.of(CommandResult.success());
                }
            }

				/*Twitch*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("twitch")){

                if(args[2].startsWith("http://") || args[2].startsWith("https://")){

                    MediaCMD(args[2], player.getName(), "twitch");
                    player.sendMessage(setTo("Twitch URL is set to: ", args[2]));

                    return Optional.of(CommandResult.success());
                }else{
                    player.sendMessage(usage("URL Is Not Valid!"));
                    player.sendMessage(example("http://google.com"));
                    player.sendMessage(example("https://google.com"));
                    return Optional.of(CommandResult.success());
                }
            }

				/*Twitter*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("twitter")){

                if(args[2].startsWith("http://") || args[2].startsWith("https://")){

                    MediaCMD(args[2], player.getName(), "twitter");
                    player.sendMessage(setTo("Twitter URL is set to: ", args[2]));

                    return Optional.of(CommandResult.success());
                }else{
                    player.sendMessage(usage("URL Is Not Valid!"));
                    player.sendMessage(example("http://google.com"));
                    player.sendMessage(example("https://google.com"));
                    return Optional.of(CommandResult.success());
                }
            }

				/*Facebook*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("facebook")){

                if(args[2].startsWith("http://") || args[2].startsWith("https://")){

                    MediaCMD(args[2], player.getName(), "facebook");
                    player.sendMessage(setTo("Facebook URL is set to: ", args[2]));

                    return Optional.of(CommandResult.success());
                }else{
                    player.sendMessage(usage("URL Is Not Valid!"));
                    player.sendMessage(example("http://google.com"));
                    player.sendMessage(example("https://google.com"));
                    return Optional.of(CommandResult.success());
                }
            }

				/*Skype*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("skype")){

                MediaCMD(args[2], player.getName(), "skype");
                player.sendMessage(setTo("Skype Name is set to: ", args[2]));

                return Optional.of(CommandResult.success());
            }

				/*Instagram*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("instagram")){

                if(args[2].startsWith("http://") || args[2].startsWith("https://")){

                    MediaCMD(args[2], player.getName(), "instagram");
                    player.sendMessage(setTo("Instagram URL is set to: ", args[2]));

                    return Optional.of(CommandResult.success());
                }else{
                    player.sendMessage(usage("URL Is Not Valid!"));
                    player.sendMessage(example("http://google.com"));
                    player.sendMessage(example("https://google.com"));
                    return Optional.of(CommandResult.success());
                }
            }

				/*Steam*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("steam")){

                MediaCMD(args[2], player.getName(), "steam");
                player.sendMessage(setTo("Steam Name is set to: ", args[2]));

                return Optional.of(CommandResult.success());
            }

				/*E-Mail*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("email")){

                if(args[2].endsWith(".com") || args[2].endsWith(".nl") || args[2].endsWith(".net") || args[2].endsWith(".org") || args[2].endsWith(".co.uk") || args[2].endsWith(".us") || args[2].endsWith(".be")){

                    MediaCMD(args[2], player.getName(), "email");
                    player.sendMessage(setTo("E-Mail is set to: ", args[2]));

                }else{
                    player.sendMessage(usage("Not A Valid E-Mail!"));
                    player.sendMessage(supported("Supported: ", "'.com', '.nl', '.net', '.org', '.be', '.co.uk', '.us'"));
                }
            }

            else{
                player.sendMessage(usage("Usage: /social [playername]"));
                player.sendMessage(usage("Usage: /social set [media] [url]"));
                player.sendMessage(usage("Usage: /social reset"));
                player.sendMessage(media1());
                player.sendMessage(media2());
            }
        }else sender.sendMessage(wrngSender());
        return Optional.of(CommandResult.success());
    }

    private final Object desc = "View someones SocialMedia via URLs";
    private final Object usage = "/<command>";

    @Override
    public boolean testPermission(CommandSource source) {
        return false;
    }

    @Override
    public Optional<Text> getShortDescription(CommandSource source) {
        return Optional.of(Texts.of(desc));
    }

    @Override
    public Optional<Text> getHelp(CommandSource source) {
        return Optional.of(Texts.of(desc));
    }

    @Override
    public Text getUsage(CommandSource source) {
        return Texts.of(usage);
    }

    @Override
    public List<String> getSuggestions(CommandSource source, String arguments) throws CommandException {
        return Collections.emptyList();
    }




}
