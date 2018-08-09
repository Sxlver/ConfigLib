package de.exlll.configlib;

import de.exlll.configlib.annotation.ConfigurationElement;

import java.util.Map;

import static de.exlll.configlib.Configuration.Properties;
import static de.exlll.configlib.configs.yaml.YamlConfiguration.YamlProperties.DEFAULT;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FieldMapperHelpers {
    @ConfigurationElement
    interface LocalTestInterface {}

    @ConfigurationElement
    static class LocalTestInterfaceImpl implements LocalTestInterface {}

    @ConfigurationElement
    static abstract class LocalTestAbstractClass {}

    @ConfigurationElement
    static class LocalTestAbstractClassImpl extends LocalTestAbstractClass {}

    @ConfigurationElement
    enum LocalTestEnum {
        S, T
    }

    static class Sub1 {}

    @ConfigurationElement
    static class Sub2 {}

    @ConfigurationElement
    static class Sub3 {
        public Sub3(int x) {}
    }

    public static void assertItmCfgExceptionMessage(Object o, String msg) {
        ConfigurationException ex = assertItmThrowsCfgException(o);
        assertThat(ex.getMessage(), is(msg));
    }

    public static void assertItmCfgExceptionMessage(
            Object o, Properties props, String msg
    ) {
        ConfigurationException ex = assertItmThrowsCfgException(o, props);
        assertThat(ex.getMessage(), is(msg));
    }


    public static void assertIfmCfgExceptionMessage(
            Object o, Map<String, Object> map, String msg
    ) {
        ConfigurationException ex = assertIfmThrowsCfgException(o, map);
        assertThat(ex.getMessage(), is(msg));
    }

    public static void assertIfmCfgExceptionMessage(
            Object o, Map<String, Object> map, Properties props, String msg
    ) {
        ConfigurationException ex = assertIfmThrowsCfgException(o, map, props);
        assertThat(ex.getMessage(), is(msg));
    }

    public static ConfigurationException assertItmThrowsCfgException(Object o) {
        return assertThrows(
                ConfigurationException.class,
                () -> instanceToMap(o)
        );
    }

    public static ConfigurationException assertItmThrowsCfgException(
            Object o, Configuration.Properties props
    ) {
        return assertThrows(
                ConfigurationException.class,
                () -> instanceToMap(o, props)
        );
    }

    public static ConfigurationException assertIfmThrowsCfgException(
            Object o, Map<String, Object> map
    ) {
        return assertThrows(
                ConfigurationException.class,
                () -> instanceFromMap(o, map)
        );
    }

    public static ConfigurationException assertIfmThrowsCfgException(
            Object o, Map<String, Object> map, Configuration.Properties props
    ) {
        return assertThrows(
                ConfigurationException.class,
                () -> instanceFromMap(o, map, props)
        );
    }

    public static Map<String, Object> instanceToMap(Object o) {
        return instanceToMap(o, DEFAULT);
    }

    public static Map<String, Object> instanceToMap(
            Object o, Configuration.Properties props) {
        return FieldMapper.instanceToMap(o, props);
    }

    public static <T> T instanceFromMap(T o, Map<String, Object> map) {
        FieldMapper.instanceFromMap(o, map, DEFAULT);
        return o;
    }

    public static <T> T instanceFromMap(
            T o, Map<String, Object> map, Configuration.Properties props) {
        FieldMapper.instanceFromMap(o, map, props);
        return o;
    }
}
