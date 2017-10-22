package skills;

import de.linzn.cbn.api.ch7466ce.CBNApi;
import de.linzn.viki.App;
import de.linzn.viki.internal.ifaces.ISkillTemplate;
import de.linzn.viki.internal.ifaces.ParentSkill;
import de.linzn.viki.internal.ifaces.SkillClient;
import de.linzn.viki.internal.ifaces.SubSkill;

public class NetworkTemplate implements ISkillTemplate {
    private SkillClient skillClient;
    private ParentSkill parentSkill;
    private SubSkill subSkill;
    private String prefix = this.getClass().getSimpleName() + "->";

    @Override
    public void setEnv(SkillClient requestOwner, ParentSkill parentSkill, SubSkill subSkill) {
        this.skillClient = requestOwner;
        this.subSkill = subSkill;
        this.parentSkill = parentSkill;
    }

    @Override
    public void newClientResponse(String[] strings) {

    }

    public boolean cbnModemRestart() {
        String cbnHost = (String) this.subSkill.serial_data.get("hostName");
        String cbnUsername = (String) this.subSkill.serial_data.get("systemUser");
        String cbnPassword = (String) this.subSkill.serial_data.get("systemPassword");
        App.logger(prefix + "cbnModemRestart-->hostName " + cbnHost);
        CBNApi api = new CBNApi(cbnHost, cbnUsername, cbnPassword);
        try {
            api.restartCBN();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
