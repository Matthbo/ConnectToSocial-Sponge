package matthbo.plugin.connecttosocial.command;

import com.google.common.base.Optional;
import matthbo.plugin.connecttosocial.ConnectToSocial;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.util.command.CommandCallable;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandSource;

import java.util.List;

public class CommandSocial implements CommandCallable {

    private ConnectToSocial CTS = ConnectToSocial.instance;

    @Override
    public boolean call(CommandSource sender, String arguments, List<String> parents) throws CommandException {
        if(sender instanceof Player){
            Player player = (Player)sender;
            String[] args = arguments.split(" ");
            if(args.length == 1 && !args[0].equalsIgnoreCase("reset")){
                Player target = (Player) CTS.getGame().getServer().get().getPlayer(args[0]);
                if(target != null){
                    fileToMessage(player, target.getName());
                    return true;
                }else{
                    player.sendMessage(pluginUsage + "Player is not online or does not exist!");
                    return true;
                }
            }
            else if(args.length == 1 && args[0].equalsIgnoreCase("reset")){

                resetFile(player.getName());
                player.sendMessage(pluginMSG + "Your Social Media List has been reset");

                return true;
            }

				/*Youtube*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("youtube")){

                if(args[2].startsWith("http://") || args[2].startsWith("https://")){

                    MediaCMD(args[2], player.getName(), "youtube");
                    player.sendMessage(pluginMSG + "Youtube URL is set to: " + ChatColor.BLUE + args[2]);

                    return true;
                }else{
                    player.sendMessage(pluginUsage + "URL Is Not Valid!");
                    player.sendMessage(pluginUsage + "Example: " + ChatColor.BLUE + "http://google.com");
                    player.sendMessage(pluginUsage + "Example: " + ChatColor.BLUE + "https://google.com");
                    return true;
                }
            }

				/*Twitch*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("twitch")){

                if(args[2].startsWith("http://") || args[2].startsWith("https://")){

                    MediaCMD(args[2], player.getName(), "twitch");
                    player.sendMessage(pluginMSG + "Twitch URL is set to: " + ChatColor.BLUE + args[2]);

                    return true;
                }else{
                    player.sendMessage(pluginUsage + "URL Is Not Valid!");
                    player.sendMessage(pluginUsage + "Example: " + ChatColor.BLUE + "http://google.com");
                    player.sendMessage(pluginUsage + "Example: " + ChatColor.BLUE + "https://google.com");
                    return true;
                }
            }

				/*Twitter*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("twitter")){

                if(args[2].startsWith("http://") || args[2].startsWith("https://")){

                    MediaCMD(args[2], player.getName(), "twitter");
                    player.sendMessage(pluginMSG + "Twitter URL is set to: " + ChatColor.BLUE + args[2]);

                    return true;
                }else{
                    player.sendMessage(pluginUsage + "URL Is Not Valid!");
                    player.sendMessage(pluginUsage + "Example: " + ChatColor.BLUE + "http://google.com");
                    player.sendMessage(pluginUsage + "Example: " + ChatColor.BLUE + "https://google.com");
                    return true;
                }
            }

				/*Facebook*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("facebook")){

                if(args[2].startsWith("http://") || args[2].startsWith("https://")){

                    MediaCMD(args[2], player.getName(), "facebook");
                    player.sendMessage(pluginMSG + "Facebook URL is set to: " + ChatColor.BLUE + args[2]);

                    return true;
                }else{
                    player.sendMessage(pluginUsage + "URL Is Not Valid!");
                    player.sendMessage(pluginUsage + "Example: " + ChatColor.BLUE + "http://google.com");
                    player.sendMessage(pluginUsage + "Example: " + ChatColor.BLUE + "https://google.com");
                    return true;
                }
            }

				/*Skype*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("skype")){

                MediaCMD(args[2], player.getName(), "skype");
                player.sendMessage(pluginMSG + "Skype Name is set to: " + ChatColor.BLUE + args[2]);

                return true;
            }

				/*Instagram*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("instagram")){

                if(args[2].startsWith("http://") || args[2].startsWith("https://")){

                    MediaCMD(args[2], player.getName(), "instagram");
                    player.sendMessage(pluginMSG + "Instagram URL is set to: " + ChatColor.BLUE + args[2]);

                    return true;
                }else{
                    player.sendMessage(pluginUsage + "URL Is Not Valid!");
                    player.sendMessage(pluginUsage + "Example: " + ChatColor.BLUE + "http://google.com");
                    player.sendMessage(pluginUsage + "Example: " + ChatColor.BLUE + "https://google.com");
                    return true;
                }
            }

				/*Steam*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("steam")){

                MediaCMD(args[2], player.getName(), "steam");
                player.sendMessage(pluginMSG + "Steam Name is set to: " + ChatColor.BLUE + args[2]);

                return true;
            }

				/*E-Mail*/
            else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("email")){

                if(args[2].endsWith(".com") || args[2].endsWith(".nl") || args[2].endsWith(".net") || args[2].endsWith(".org") || args[2].endsWith(".co.uk") || args[2].endsWith(".us") || args[2].endsWith(".be")){

                    MediaCMD(args[2], player.getName(), "email");
                    player.sendMessage(pluginMSG + "E-Mail is set to: " + ChatColor.BLUE + args[2]);

                }else{
                    player.sendMessage(pluginUsage + "Not A Valid E-Mail!");
                    player.sendMessage(pluginUsage + "Supported: " + ChatColor.BLUE + "'.com', '.nl', '.net', '.org', '.be', '.co.uk', '.us'");
                }
            }

            else{
                player.sendMessage(pluginUsage + "Usage: /social [playername]");
                player.sendMessage(pluginUsage + "Usage: /social set [media] [url]");
                player.sendMessage(pluginUsage + "Usage: /social reset");
                player.sendMessage(pluginMSG + "---[Media]---");
                player.sendMessage(pluginMSG + "" + ChatColor.BLUE + "youtube, twitch, twitter, facebook, skype, instagram, steam, email");
            }
        }else sender.sendMessage(pluginUsage + "Player Command Only!");
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
}
