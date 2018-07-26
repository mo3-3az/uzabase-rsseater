package com.uzabase.rsseater.convert;

import java.io.InputStream;

public interface FeedsConverter {

    String convert(InputStream feeds);
}
