import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: 'login', 
    loadChildren: () => import('./modules/login/login.module').then((m) => m.LoginModule)
  },
  {
    path: 'scheduling', 
    loadChildren: () => import('./modules/scheduling/scheduling.module').then((m) => m.SchedulingModule)
  },
  {
    path: '', 
    loadChildren: () => import('./modules/login/login.module').then((m) => m.LoginModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
