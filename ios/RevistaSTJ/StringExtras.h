//
//  StringMethods.h
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 31/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface NSString (StringExtras)

- (BOOL)containsString:(NSString *)aString;
- (BOOL)containsString:(NSString *)aString ignoringCase:(BOOL)flag;
- (NSString *) trimWhiteSpaces;
- (NSString *) stringWithoutLineBreak;

@end
