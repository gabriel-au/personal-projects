//
//  StringMethods.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 31/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "StringExtras.h"


@implementation NSString (StringExtras)


- (BOOL)containsString:(NSString *)aString
{
    return [self containsString:aString ignoringCase:NO];
}

- (BOOL)containsString:(NSString *)aString ignoringCase:(BOOL)flag
{
    unsigned mask = (flag ? NSCaseInsensitiveSearch : 0);
    NSRange range = [self rangeOfString:aString options:mask];
    return (range.length > 0);
}
- (NSString *) trimWhiteSpaces
{
	return [self stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceCharacterSet]];
}

- (NSString *) stringWithoutLineBreak; {
	NSString *str = [self stringByReplacingOccurrencesOfString: @"\n" withString: @" "];
	str = [str stringByReplacingOccurrencesOfString: @"\r" withString: @" "];
	return str;
}

@end
