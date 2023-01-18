import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CustomerListComponent} from "./components/customer-list/customer-list.component";
import {VehicleListComponent} from "./components/vehicle-list/vehicle-list.component";
import {LeasingContractListComponent} from "./components/leasing-contract-list/leasing-contract-list.component";

const routes: Routes = [
  {path: 'customers', component: CustomerListComponent},
  {path: 'vehicles', component: VehicleListComponent},
  {path: 'leasing-contracts', component: LeasingContractListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
