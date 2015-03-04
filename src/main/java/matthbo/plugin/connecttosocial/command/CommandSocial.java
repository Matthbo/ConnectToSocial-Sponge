package matthbo.plugin.connecttosocial.command;

import com.google.common.base.Optional;
import matthbo.plugin.connecttosocial.ConnectToSocial;
import matthbo.plugin.connecttosocial.Refs;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.message.Message;
import org.spongepowered.api.text.message.Messages;
import org.spongepowered.api.util.command.CommandCallable;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandSource;

import java.io.*;
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

    private Message playersMedia(String target){
        return Messages.builder(Refs.pluginMSG).color(TextColors.DARK_GREEN).append(Messages.builder(target).color(TextColors.RESET).append(Messages.builder("'s Social Media:").build()).build()).build();
    }

    private Message noList(){
        return Messages.builder(Refs.pluginMSG).color(TextColors.DARK_GREEN).append(Messages.of("Nothing!")).build();
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

    @Override
    public boolean call(CommandSource sender, String arguments, List<String> parents) throws CommandException {
        if(sender instanceof Player){
            Player player = (Player)sender;
            String[] args = arguments.split(" ");
            if(args.length == 1 && !args[0].equalsIgnoreCase("reset") && !(args[0].length() < 2)){
                Player target = CTS.getGame().getServer().get().getPlayer(args[0]).orNull();
                if(target != null){
                    fileToMessage(player, target.getName());
                    return true;
                }else{
                    player.sendMessage(usage("Player is not online or does not exist!"));
                    return true;
                }
            }
            else if(args.length == 1 && args[0].equalsIgnoreCase("reset")){

                resetFile(player.getName());
                player.sendMessage(usage("Your Social Media List has been reset"));

                return true;
            }

				/*Youtube*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("youtube")){

                if(args[2].startsWith("http://") || args[2].startsWith("https://")){

                    MediaCMD(args[2], player.getName(), "youtube");
                    player.sendMessage(setTo("Youtube URL is set to: ", args[2]));

                    return true;
                }else{
                    player.sendMessage(usage("URL Is Not Valid!"));
                    player.sendMessage(example("http://google.com"));
                    player.sendMessage(example("https://google.com"));
                    return true;
                }
            }

				/*Twitch*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("twitch")){

                if(args[2].startsWith("http://") || args[2].startsWith("https://")){

                    MediaCMD(args[2], player.getName(), "twitch");
                    player.sendMessage(setTo("Twitch URL is set to: ", args[2]));

                    return true;
                }else{
                    player.sendMessage(usage("URL Is Not Valid!"));
                    player.sendMessage(example("http://google.com"));
                    player.sendMessage(example("https://google.com"));
                    return true;
                }
            }

				/*Twitter*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("twitter")){

                if(args[2].startsWith("http://") || args[2].startsWith("https://")){

                    MediaCMD(args[2], player.getName(), "twitter");
                    player.sendMessage(setTo("Twitter URL is set to: ", args[2]));

                    return true;
                }else{
                    player.sendMessage(usage("URL Is Not Valid!"));
                    player.sendMessage(example("http://google.com"));
                    player.sendMessage(example("https://google.com"));
                    return true;
                }
            }

				/*Facebook*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("facebook")){

                if(args[2].startsWith("http://") || args[2].startsWith("https://")){

                    MediaCMD(args[2], player.getName(), "facebook");
                    player.sendMessage(setTo("Facebook URL is set to: ", args[2]));

                    return true;
                }else{
                    player.sendMessage(usage("URL Is Not Valid!"));
                    player.sendMessage(example("http://google.com"));
                    player.sendMessage(example("https://google.com"));
                    return true;
                }
            }

				/*Skype*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("skype")){

                MediaCMD(args[2], player.getName(), "skype");
                player.sendMessage(setTo("Skype Name is set to: ", args[2]));

                return true;
            }

				/*Instagram*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("instagram")){

                if(args[2].startsWith("http://") || args[2].startsWith("https://")){

                    MediaCMD(args[2], player.getName(), "instagram");
                    player.sendMessage(setTo("Instagram URL is set to: ", args[2]));

                    return true;
                }else{
                    player.sendMessage(usage("URL Is Not Valid!"));
                    player.sendMessage(example("http://google.com"));
                    player.sendMessage(example("https://google.com"));
                    return true;
                }
            }

				/*Steam*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("steam")){

                MediaCMD(args[2], player.getName(), "steam");
                player.sendMessage(setTo("Steam Name is set to: ", args[2]));

                return true;
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
        return true;
    }

    @Override
    public boolean testPermission(CommandSource source) {
        return false;
    }

    @Override
    public Optional<String> getShortDescription() {
        return null;
    }

    @Override
    public Optional<String> getHelp() {
        return null;
    }

    @Override
    public String getUsage() {
        return null;
    }

    @Override
    public List<String> getSuggestions(CommandSource source, String arguments) throws CommandException {
        return null;
    }

    private Message wrngSender(){
        return Messages.builder(Refs.pluginMSG).color(TextColors.DARK_GREEN).append(Messages.builder("Player Command Only!").color(TextColors.RED).build()).build();
    }

    private Message media1(){
        return Messages.builder(Refs.pluginMSG).color(TextColors.DARK_GREEN).append(Messages.builder("---[Media]---").color(TextColors.RESET).build()).build();
    }

    private Message media2(){
        return Messages.builder(Refs.pluginMSG).color(TextColors.DARK_GREEN).append(Messages.builder("youtube, twitch, twitter, facebook, skype, instagram, steam, email").color(TextColors.BLUE).build()).build();
    }

    private Message usage(String usage){
        return Messages.builder(Refs.pluginMSG).color(TextColors.DARK_GREEN).append(Messages.builder(usage).color(TextColors.RED).build()).build();
    }

    private Message example(String example){
        return Messages.builder(Refs.pluginMSG).color(TextColors.DARK_GREEN).append(Messages.builder("Example: ").color(TextColors.RED).append(Messages.builder(example).color(TextColors.BLUE).build()).build()).build();
    }

    private Message setTo(String msg1, String msg2){
        return Messages.builder(Refs.pluginMSG).color(TextColors.DARK_GREEN).append(Messages.builder(msg1).color(TextColors.RESET).append(Messages.builder(msg2).color(TextColors.BLUE).build()).build()).build();
    }

    private  Message supported(String msg1, String msg2){
        return Messages.builder(Refs.pluginMSG).color(TextColors.DARK_GREEN).append(Messages.builder(msg1).color(TextColors.RED).append(Messages.builder(msg2).color(TextColors.BLUE).build()).build()).build();
    }
}
