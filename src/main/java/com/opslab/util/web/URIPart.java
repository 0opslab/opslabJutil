package com.opslab.util.web;

import com.opslab.util.CharUtil;

/**
 * Enumeration to identify the parts of a URI.
 * <p/>
 * Contains methods to indicate whether a given character is valid in a specific URI component.
 *
 * @see <a href="http://www.ietf.org/rfc/rfc3986.txt">RFC 3986</a>
 */
public enum URIPart {

    SCHEME {
        @Override
        public boolean isValid(char c) {
            return CharUtil.isAlpha(c) || CharUtil.isDigit(c) || c == '+' || c == '-' || c == '.';
        }
    },
    //		AUTHORITY {
//			@Override
//			public boolean isValid(char c) {
//				return isUnreserved(c) || isSubDelimiter(c) || c == ':' || c == '@';
//			}
//		},
    USER_INFO {
        @Override
        public boolean isValid(char c) {
            return CharUtil.isUnreserved(c) || CharUtil.isSubDelimiter(c) || c == ':';
        }
    },
    HOST {
        @Override
        public boolean isValid(char c) {
            return CharUtil.isUnreserved(c) || CharUtil.isSubDelimiter(c);
        }
    },
    PORT {
        @Override
        public boolean isValid(char c) {
            return CharUtil.isDigit(c);
        }
    },
    PATH {
        @Override
        public boolean isValid(char c) {
            return CharUtil.isPchar(c) || c == '/';
        }
    },
    PATH_SEGMENT {
        @Override
        public boolean isValid(char c) {
            return CharUtil.isPchar(c);
        }
    },
    QUERY {
        @Override
        public boolean isValid(char c) {
            return CharUtil.isPchar(c) || c == '/' || c == '?';
        }
    },
    QUERY_PARAM {
        @Override
        public boolean isValid(char c) {
            if (c == '=' || c == '+' || c == '&' || c == ';') {
                return false;
            }
            return CharUtil.isPchar(c) || c == '/' || c == '?';
        }
    },
    FRAGMENT {
        @Override
        public boolean isValid(char c) {
            return CharUtil.isPchar(c) || c == '/' || c == '?';
        }
    };

    /**
     * Indicates whether the given character is allowed in this URI component.
     *
     * @return <code>true</code> if the character is allowed; {@code false} otherwise
     */
    public abstract boolean isValid(char c);

}