package funkemunky.Daedalus.check.combat;

import java.util.WeakHashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

import funkemunky.Daedalus.check.Check;
import funkemunky.Daedalus.utils.Chance;
import funkemunky.Daedalus.Daedalus;

public class AutoclickerD extends Check {
    private WeakHashMap<Player, ClickProfile> profiles = new WeakHashMap();
    private WeakHashMap<Player, ClickProfile2> profiles2 = new WeakHashMap();

    public AutoclickerD(Daedalus Daedalus) {
        super("AutoClickerD", "AutoClicker (Type D)", Daedalus);
        
        this.setEnabled(true);
        this.setBannable(true);
        setMaxViolations(5);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent playerInteractEvent) {
        if (!this.isEnabled()) {
            return;
        }
        Player player = playerInteractEvent.getPlayer();
        ClickProfile clickProfile = null;
        ClickProfile2 clickProfile2 = null;
        if (!this.profiles.containsKey((Object)player)) {
            clickProfile = new ClickProfile();
            this.profiles.put(player, clickProfile);
        } else {
            clickProfile = this.profiles.get(player);
        }
        if (!this.profiles2.containsKey((Object)player)) {
            clickProfile2 = new ClickProfile2();
            this.profiles2.put(player, clickProfile2);
        } else {
            clickProfile2 = this.profiles2.get(player);
        }
        clickProfile.issueClick(player, this);
        clickProfile2.issueClick(player, this);
    }

    public class ClickProfile {
        public double clicks;
        private long clickSprint;
        private double lastCPS;
        private double twoSecondsAgoCPS;
        private double threeSecondsAgoCPS;
        private int violations;

        public ClickProfile() {
            this.clicks = 0.0;
            this.clickSprint = 0;
            this.lastCPS = 0.0;
            this.twoSecondsAgoCPS = 0.0;
            this.threeSecondsAgoCPS = 0.0;
            this.violations = 0;
        }

        public void issueClick(Player player, Check check) {
            long l = System.currentTimeMillis();
            if (l - this.clickSprint >= 1000) {
                this.shuffleDown();
                this.clickSprint = l;
                this.clicks = 0.0;
                double d = this.lastCPS;
                double d2 = this.twoSecondsAgoCPS;
                double d3 = this.threeSecondsAgoCPS;
                if (d == 9.0 && d2 == 11.0 && d3 == 10.0 || d == 9.0 && d2 == 8.0 && d3 == 10.0) {
                    ++this.violations;
                    if (this.violations >= 1) {
                        AutoclickerD.this.getDaedalus().logCheat(AutoclickerD.this, player, "Vape Clicker Type A : [Patterns]", Chance.LIKELY, new String[] {"Experimental"});;
                    }
                }
            }
            this.clicks += 1.0;
        }

        private void shuffleDown() {
            this.threeSecondsAgoCPS = this.twoSecondsAgoCPS;
            this.twoSecondsAgoCPS = this.lastCPS;
            this.lastCPS = this.clicks;
        }
    }
    public class ClickProfile2 {
        public double clicks;
        private long clickSprint;
        private double lastCPS;
        private double twoSecondsAgoCPS;
        private double threeSecondsAgoCPS;
        private int violations;

        public ClickProfile2() {
            this.clicks = 0.0;
            this.clickSprint = 0;
            this.lastCPS = 0.0;
            this.twoSecondsAgoCPS = 0.0;
            this.threeSecondsAgoCPS = 0.0;
            this.violations = 0;
        }

        public void issueClick(Player player, Check check) {
            long l = System.currentTimeMillis();
            if (l - this.clickSprint >= 1000) {
                this.shuffleDown();
                this.clickSprint = l;
                this.clicks = 0.0;
                double d = this.lastCPS;
                double d2 = this.twoSecondsAgoCPS;
                double d3 = this.threeSecondsAgoCPS;
                if (d == 9.0 && d2 == 11.0 && d3 == 10.0 || d == 9.0 && d2 == 8.0 && d3 == 10.0) {
                    System.out.println("[0x01]: " + player.getName() + "logged for a Pattern of VIDJDEI");
                    ++this.violations;
                    if (this.violations >= 1) {
                        AutoclickerD.this.getDaedalus().logCheat(AutoclickerD.this, player, "Vape Clicker Type B : [Patterns]", Chance.LIKELY, new String[0]);
                    }
                }
            }
            this.clicks += 1.0;
        }

        private void shuffleDown() {
            this.threeSecondsAgoCPS = this.twoSecondsAgoCPS;
            this.twoSecondsAgoCPS = this.lastCPS;
            this.lastCPS = this.clicks;
        }
    }
}