//
//  AgendaTableCell.h
//  EasyBand
//
//  Created by Eduardo Carminati on 22/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
@class Agenda;

@interface AgendaTableCell : UITableViewCell {
	UILabel *dayLabel;
	UILabel *monthLabel;
	UILabel *cityLabel;
	UILabel *addressLabel;
	//
	NSInteger *itemID; 

}
	
- (void) setData: (Agenda *) agenda;
-(UILabel *)newLabelWithPrimaryColor:(UIColor *)primaryColor selectedColor:(UIColor *)selectedColor fontSize:(CGFloat)fontSize bold:(BOOL)bold;

@property (nonatomic, retain) UILabel *dayLabel;
@property (nonatomic, retain) UILabel *monthLabel;
@property (nonatomic, retain) UILabel *addressLabel;
@property (nonatomic, retain) UILabel *cityLabel;
//
@property (nonatomic, assign) NSInteger *itemID;

@end
