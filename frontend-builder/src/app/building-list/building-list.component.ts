import { Component, OnInit } from '@angular/core';
import {MatDialog, MatSnackBar, MatTableDataSource} from '@angular/material';
import {Building} from '../building';
import {BuildingService} from './building.service';
import {Router} from '@angular/router';
import {PostDialogComponent} from '../post-dialog/post-dialog.component';

@Component({
  selector: 'app-building-list',
  templateUrl: './building-list.component.html',
  styleUrls: ['./building-list.component.css']
})
export class BuildingListComponent implements OnInit {
  buildings: MatTableDataSource<Building> = new MatTableDataSource();
  displayedColumns = ['id', 'name', 'area', 'cube', 'light', 'heating', 'detail'];
  constructor(
    private buildingService: BuildingService,
    private router: Router,
    public dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.getBuildings();
  }

  getBuildings(): void {
    this.buildingService.getBuildings().subscribe(resp => {
      this.buildings.data = resp;
    });
  }

  onDetailClick(building: Building): void {
    this.router.navigate(['buildings', building.id]);
  }

  createBuildingDialog(): void {
    const dialogRef = this.dialog.open(PostDialogComponent, {
      width: '250px',
      data: { type: 'Building', name: ''}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.buildingService.createBuilding({name: result.name})
        .subscribe(resp => {
          this.snackbar.open('Successfully created', '', {
            duration: 1500
          });
        }, err => {
          this.snackbar.open('Could not create', '', {
            duration: 1500
          });
        }, () => {
          this.getBuildings();
        });
    });
  }

}
