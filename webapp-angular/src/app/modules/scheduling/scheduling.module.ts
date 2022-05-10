import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SchedulingRoutingModule } from './scheduling-routing.module';
import { SchedulingPageComponent } from './scheduling-page/scheduling-page.component';
import { ScheduledListComponent } from './scheduling-page/scheduled-list/scheduled-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SchedulingFormComponent } from './scheduling-page/scheduling-form/scheduling-form.component';
import { HttpClientModule } from '@angular/common/http'

@NgModule({
  declarations: [
    SchedulingPageComponent,
    ScheduledListComponent,
    SchedulingFormComponent
  ],
  imports: [
    CommonModule,
    SchedulingRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ]
})
export class SchedulingModule { }
