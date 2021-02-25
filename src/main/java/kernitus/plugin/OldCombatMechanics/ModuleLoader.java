package kernitus.plugin.OldCombatMechanics;

import kernitus.plugin.OldCombatMechanics.module.Module;
import kernitus.plugin.OldCombatMechanics.utilities.EventRegistry;
import kernitus.plugin.OldCombatMechanics.utilities.Messenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rayzr522 on 6/27/16.
 */
public class ModuleLoader {

    private static EventRegistry eventRegistry;
    private static List<Module> modules = new ArrayList<>();
    private static Map<String, Module> moduleMap = new HashMap<>();

    public static void initialise(OCMMain plugin){
        ModuleLoader.eventRegistry = new EventRegistry(plugin);
    }

    public static void toggleModules(){
        modules.forEach(module -> setState(module, module.isEnabled()));
    }

    private static void setState(Module module, boolean state){
        if(state){
            if(eventRegistry.registerListener(module)){
                Messenger.debug("Enabled " + module.getClass().getSimpleName());
            }
        } else {
            if(eventRegistry.unregisterListener(module)){
                Messenger.debug("Disabled " + module.getClass().getSimpleName());
            }
        }
    }

    public static void addModule(Module module){
        modules.add(module);
        moduleMap.put(module.getConfigName(), module);
    }

    public static Module getModule(String configName) {
        return moduleMap.get(configName);
    }

    public static List<Module> getModules(){
        return modules;
    }
}
