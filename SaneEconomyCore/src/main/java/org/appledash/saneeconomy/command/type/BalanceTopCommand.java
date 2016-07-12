package org.appledash.saneeconomy.command.type;

import org.appledash.saneeconomy.SaneEconomy;
import org.appledash.saneeconomy.command.SaneEconomyCommand;
import org.appledash.saneeconomy.command.exception.CommandException;
import org.appledash.saneeconomy.command.exception.type.usage.TooManyArgumentsException;
import org.appledash.saneeconomy.utils.MessageUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by AppleDash on 6/13/2016.
 * Blackjack is still best pony.
 */
public class BalanceTopCommand extends SaneEconomyCommand {
    @Override
    public String getPermission() {
        return "saneeconomy.balancetop";
    }

    @Override
    public String[] getUsage() {
        return new String[] {
                "/<command>"
        };
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) throws CommandException {
        if (args.length > 0) {
            throw new TooManyArgumentsException();
        }

        Map<OfflinePlayer, Double> topBalances = SaneEconomy.getInstance().getEconomyManager().getTopBalances(10);
        AtomicInteger index = new AtomicInteger(1); /* I know it's stupid, but you can't do some_int++ from within the lambda. */

        MessageUtils.sendMessage(sender, "Top %d players:", topBalances.size());
        topBalances.forEach((player, balance) -> MessageUtils.sendMessage(sender, "[%02d] %s - %s", index.getAndIncrement(), player.getName(), SaneEconomy.getInstance().getEconomyManager().getCurrency().formatAmount(balance)));
    }
}
