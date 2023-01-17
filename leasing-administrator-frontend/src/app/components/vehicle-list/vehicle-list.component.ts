import {Component, OnInit} from '@angular/core';
import {Vehicle} from "../../dto/vehicle";
import {VehicleService} from "../../services/vehicle.service";

@Component({
  selector: 'app-vehicle-list',
  templateUrl: './vehicle-list.component.html',
  styleUrls: ['./vehicle-list.component.scss']
})
export class VehicleListComponent implements OnInit{

  displayForm = false;
  selectedVehicle: Vehicle = new Vehicle();
  vehicles: Array<Vehicle> = new Array<Vehicle>();


  constructor(private vehicleService: VehicleService) {
  }

  ngOnInit(): void {
    this.getListOfVehicles();
  }

  createNewVehicle() {
    this.displayForm = true;
    this.selectedVehicle = new Vehicle();
  }

  getListOfVehicles() {
    this.vehicleService.getVehicleList().subscribe(res => {
      this.vehicles = res;
    })
  }

  editVehicle(vehicle: Vehicle) {
    vehicle = JSON.parse(JSON.stringify(vehicle))
    this.selectedVehicle = vehicle;
    this.displayForm = true;
  }

  onVehicleUpdate(vehicle: Vehicle) {
    const oldVehicleIndex = this.vehicles.findIndex(c => c.id === vehicle.id);
    if (oldVehicleIndex < 0) {
      return;
    }
    this.vehicles.splice(oldVehicleIndex, 1, vehicle);
  }

  onVehicleAdded(vehicle: Vehicle) {
    this.vehicles.splice(0, 0, vehicle);
  }
}
