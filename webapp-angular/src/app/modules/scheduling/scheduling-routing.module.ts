import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SchedulingPageComponent } from './scheduling-page/scheduling-page.component';


const routes: Routes = [
  {
    path: '',
    component: SchedulingPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SchedulingRoutingModule { }
