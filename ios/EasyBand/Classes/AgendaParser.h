//
//  AgendaParser.h
//  EasyBand
//
//  Created by Eduardo Carminati on 21/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Agenda.h"

@interface AgendaParser : NSObject  <NSXMLParserDelegate>  {
	
	NSMutableArray *agendas;

}

@property (nonatomic,retain) NSMutableArray *agendas;

@end
