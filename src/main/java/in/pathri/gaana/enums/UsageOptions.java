package in.pathri.gaana.enums;

import in.pathri.gaana.constants.Global;

public enum UsageOptions {
	LISTEN_FROM_EXTENSION(Global.USAGE_OPTION_EXTENSION_LISTEN, 1),
	NEW_SEARCH(Global.USAGE_OPTION_NEW_SEARCH, 2), 
	GENERATE_DOWNLOAD_LINKS(Global.USAGE_OPTION_GENERATE_DOWNLOAD_LINKS, 3), 
	DOWNLOAD_FROM_GENERATED_LINKS(Global.USAGE_OPTION_DOWNLOAD_FROM_GENERATED_LINKS, 4), 
	COVERT_DOWNLOADED_SONGS(Global.USAGE_OPTION_COVERT_DOWNLOADED_SONGS, 5);

	private String value;
	private int option;

	UsageOptions(final String value, final int option) {
		this.value = value;
		this.option = option;
	}

	public int getOption() {
		return option;
	}

	public String getValue() {
		return value;
	}

	public static UsageOptions getEnum(int value) {
		for (UsageOptions v : values())
			if (v.getOption() == value)
				return v;
		throw new IllegalArgumentException();
	}

	@Override
	public String toString() {
		return this.getValue();
	}
}
