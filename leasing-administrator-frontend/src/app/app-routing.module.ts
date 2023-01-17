import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CustomerListComponent} from "./components/customer-list/customer-list.component";
import {VehicleListComponent} from "./components/vehicle-list/vehicle-list.component";

const routes: Routes = [
  {path: 'customers', component: CustomerListComponent},
  {path: 'vehicles', component: VehicleListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
