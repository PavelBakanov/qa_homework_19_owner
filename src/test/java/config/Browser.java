package config;

public enum Browser {
    FIREFOX,
    CHROME;

    public String browserToLowerCase()
    {
        return this.name().toLowerCase();
    }
}
